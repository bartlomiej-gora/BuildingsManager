package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.entity.ProposalData;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.GroupNames;
import com.github.bgora.beans.entity.constants.ProcessName;
import com.github.bgora.beans.entity.constants.ProposalStatus;
import com.github.bgora.beans.entity.constants.RoleTypes;
import com.github.bgora.beans.session.local.UserBeanLocal;
import com.github.bgora.beans.session.remote.UserBeanRemote;
import com.github.bgora.utils.LoginUtils;
import org.apache.log4j.Logger;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementacja Serwisu u�ytwkonik�w w podstaci session beana EJB 3.0
 * 
 * @see javax.ejb.Stateless
 * 
 * @author Bart�omiej G�ra
 * 
 */

@Stateless(name = "UserBean")
@Clustered
public class UserBean implements UserBeanRemote, UserBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(UserBean.class);

	/**
	 * Domy�lny konstruktor
	 */
	public UserBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) throws RemoteException {
		return entityManager.find(User.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(User entity) throws RemoteException {
		if (entity.getUserId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserBeanService#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User login(String login, String passwd) {
		Query q = entityManager.createNamedQuery("getLoginCount");
		q.setParameter("login", login);
		q.setParameter("passwd", passwd);
		Long count = (Long) q.getSingleResult();
		assert count == 0 || count == 1 : "Mo�e by� tylko jeden (lub 0) u�ytkownik o podanym loginie!";
		if (count == 0) {
			return null;
		}
		q = entityManager.createNamedQuery("loginQuery");
		q.setParameter("login", login);
		q.setParameter("passwd", passwd);
		return (User) q.getSingleResult();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.remote.UserBeanRemote#initData()
	 */
	public void initData() throws RemoteException {
		/*
		 * Sprawdzanie r�l.
		 */
		Query q = entityManager.createNamedQuery("getAllRolesCount");
		Long result = (Long) q.getSingleResult(); // NOSONAR
		assert (result == 3) || (result == 0) : "Powinny by� tylko 3 role, lub �adna zdefiniowana!";
		if (result == 0) {

			Role role = new Role();
			role.setRole(RoleTypes.ADMIN);
			entityManager.persist(role);
			role = new Role();
			role.setRole(RoleTypes.USER);
			entityManager.persist(role);
			role = new Role();
			role.setRole(RoleTypes.CLIENT);
			entityManager.persist(role);
		}

		/*
		 * Sprawdzanie grup.
		 */
		q = entityManager.createNamedQuery("getGroupCount");
		result = (Long) q.getSingleResult();

		if (result == 0) {
			BMSGroup group = new BMSGroup();
			group.setGroupName(GroupNames.OFFICE);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.TECHNICAL_DEP);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.FINANCE_DEP);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.MANAGEMENT);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.BOSS);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.APPLICATION_ADMINISTRATOR);
			entityManager.persist(group);
			group = new BMSGroup();
			group.setGroupName(GroupNames.CLIENT);
			entityManager.persist(group);
		}

		/*
		 * Sprawdzanie u�ytkownika.
		 */

		q = entityManager.createNamedQuery("getAdminUserCount");
		result = (Long) q.getSingleResult(); // NOSONAR
		if (result == 0) {
			User admin = new User();
			admin.setLogin("admin");
			q = entityManager.createNamedQuery("getRoleByName");
			q.setParameter("role", RoleTypes.ADMIN);
			Role adminRole = (Role) q.getSingleResult();
			admin.setRole(adminRole);
			try {
				admin.setPasswd(LoginUtils.getHashPassword("adminadmin"));
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage(), e);
			}
			Person adminPerson = new Person();
			adminPerson.setName("Administrator");
			adminPerson.setSurname("U�ytkownik administracyjny");
			adminPerson.setUser(admin);
			admin.setPerson(adminPerson);
			q = entityManager.createNamedQuery("getGroupByName");
			q.setParameter("groupName", GroupNames.APPLICATION_ADMINISTRATOR);
			BMSGroup group = (BMSGroup) q.getSingleResult();
			List<BMSGroup> groups = new LinkedList<BMSGroup>();
			groups.add(group);
			admin.setGroups(groups);
			entityManager.persist(admin);
			entityManager.persist(adminPerson);
		}

		/*
		 * Tworzymy szablony proces�w biznesowych.
		 */
		q = entityManager.createNamedQuery("getProcessTemplatesCount");
		result = (Long) q.getSingleResult();
		if (result == 0) {
			/*
			 * Sk��damy wniosek o roz�o�enie p�atno�ci na raty
			 */
			MainProcess process = new MainProcess();
			List<ProcessPathElement> elements = process.getTemplatePathes();
			process.setActual(Boolean.FALSE);
			process
					.setProcessName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT);
			process
					.setDisplayName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							.getDisplayName());
			// w momecie z�o�enia wniosku musi by� zapisany do bazy w tai
			// spos�b:
			ProcessPathElement applyProposal = new ProcessPathElement();
			applyProposal.setActual(Boolean.FALSE);
			applyProposal
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_APLLY");
			applyProposal.setStatus(ProposalStatus.APPLIED);
			applyProposal.setProcessTemplate(process);
			applyProposal.setOrderNumber(1);
			applyProposal.setPlacement("Z\u0142o\u017Cono");
			applyProposal.setDisplayName("Z\u0142o\u017Cenie wniosku");
			// Dodajmy szablon pisma:
			ProposalData content = new ProposalData();
			content.setActual(Boolean.FALSE);
			StringBuilder builder = new StringBuilder();
			builder
					.append("Zwracam si\u0119 z uprzejm\u0105 pro\u015Bb\u0105 o roz\u0142o\u017Cenie p\u0142atno\u015Bci za m\u00F3j czynsz na [poda\u0107 ilo\u015B\u0107 rat] miesi\u0119cy.\n");
			builder
					.append("Pro\u015Bb\u0119 sw\u0105 motywuj\u0119 [poda\u0107 pow\u00F3d].\n\n");
			builder.append("Z powa\u017Caniem.\n");
			content.setContent(builder.toString());
			content.setMainProcess(process);
			applyProposal.setContent(content);
			applyProposal.setResponsible(findByGroupName(GroupNames.CLIENT));
			elements.add(applyProposal);
			// Potem system zbiera wnioski ze stanem processing....
			// i wrzucamy je do takiego szablonu:
			// sekretarka przeglada wniosek pod kontem prwawidlowosci.
			ProcessPathElement officeProcess = new ProcessPathElement();
			officeProcess.setActual(Boolean.FALSE);
			officeProcess
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_PROCCESS");
			officeProcess.setStatus(ProposalStatus.PROCESSING);
			officeProcess.setProcessTemplate(process);
			officeProcess.setOrderNumber(2);
			officeProcess.setPlacement("Sekretariat");
			officeProcess
					.setDisplayName("Analiza wniosku - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			officeProcess.setResponsible(findByGroupName(GroupNames.OFFICE));
			elements.add(officeProcess);

			// ksiegowosc wyraza opinie.
			ProcessPathElement bookkeepping = new ProcessPathElement();
			bookkeepping.setActual(Boolean.FALSE);
			bookkeepping
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_PROCCESS");
			bookkeepping.setStatus(ProposalStatus.PROCESSING);
			bookkeepping.setProcessTemplate(process);
			bookkeepping.setOrderNumber(3);
			bookkeepping.setPlacement("Ksi\u0119gowo\u015B\u0107");
			bookkeepping
					.setDisplayName("Analiza wniosku - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			bookkeepping
					.setResponsible(findByGroupName(GroupNames.FINANCE_DEP));
			elements.add(bookkeepping);
			// szef sie zgadza...
			ProcessPathElement bossAccept = new ProcessPathElement();
			bossAccept.setActual(Boolean.FALSE);
			bossAccept
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_PROCCESS");
			bossAccept.setStatus(ProposalStatus.PROCESSING);
			bossAccept.setProcessTemplate(process);
			bossAccept.setOrderNumber(4);
			bossAccept.setPlacement("Zarz\u0105d");
			bossAccept
					.setDisplayName("Analiza wniosku - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			elements.add(bossAccept);
			bossAccept.setResponsible(findByGroupName(GroupNames.BOSS));
			// szef sie zgadza wysyla do ssekretarki.
			ProcessPathElement officeRealize = new ProcessPathElement();
			officeRealize.setActual(Boolean.FALSE);
			officeRealize
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_PROCCESS");
			officeRealize.setStatus(ProposalStatus.ACCEPTED);
			officeRealize.setProcessTemplate(process);
			officeRealize.setOrderNumber(5);
			officeRealize.setPlacement("Sekretariat");
			officeRealize
					.setDisplayName("Zgoda - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			elements.add(officeRealize);
			officeRealize.setResponsible(findByGroupName(GroupNames.OFFICE));

			// sekretarka powiadamia klienta o zgodzie:
			ProcessPathElement successRequest = new ProcessPathElement();
			successRequest.setActual(Boolean.FALSE);
			successRequest
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_PROCCESS");
			successRequest.setStatus(ProposalStatus.COMPLETED);
			successRequest.setProcessTemplate(process);
			successRequest.setOrderNumber(6);
			successRequest.setPlacement("Powiadomienie klienta");
			successRequest
					.setDisplayName("Zgoda - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			elements.add(successRequest);
			successRequest.setResponsible(findByGroupName(GroupNames.CLIENT));
			// odrzucony na kt�rym� momencie?
			// jesli sekretarka:
			ProcessPathElement officeRefuse = new ProcessPathElement();
			officeRefuse.setActual(Boolean.FALSE);
			officeRefuse
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_REFUSED");
			officeRefuse.setStatus(ProposalStatus.REFUSED);
			officeRefuse.setProcessTemplate(process);
			officeRefuse.setOrderNumber(2);
			officeRefuse
					.setDisplayName("Odrzucono - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			officeRefuse.setPlacement("Powiadomienie klienta");
			officeRefuse.setResponsible(findByGroupName(GroupNames.CLIENT));
			elements.add(officeRefuse);

			// jesli szef lub ksiegowa:
			ProcessPathElement processRefused = new ProcessPathElement();
			processRefused.setActual(Boolean.FALSE);
			processRefused
					.setProcessPathElementName(ProcessName.REQUEST_FOR_DISTRIBUTION_OF_PAYMENT
							+ "_REFUSED");
			processRefused.setStatus(ProposalStatus.REFUSED);
			processRefused.setProcessTemplate(process);
			processRefused.setOrderNumber(1);
			processRefused.setPlacement("Powiadomienie klienta");
			processRefused
					.setDisplayName("Odrzucono - Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci");
			elements.add(processRefused);
			processRefused.setResponsible(findByGroupName(GroupNames.OFFICE));
			// kolejno��:
			applyProposal.setNextStep(officeProcess);
			officeProcess.setPrevStep(applyProposal);
			officeProcess.setRefuseStep(officeRefuse);
			officeProcess.setNextStep(bookkeepping);
			bookkeepping.setPrevStep(officeProcess);
			bookkeepping.setRefuseStep(processRefused);
			bookkeepping.setNextStep(bossAccept);
			bossAccept.setPrevStep(bookkeepping);
			bossAccept.setRefuseStep(processRefused);
			bossAccept.setNextStep(officeRealize);
			officeRealize.setPrevStep(bossAccept);
			officeRealize.setNextStep(successRequest);
			successRequest.setPrevStep(officeRealize);
			processRefused.setNextStep(officeRefuse);
			// Zapiszmy te schematy:
			process.setTemplatePathes(elements);
			entityManager.persist(process);

			/*
			 * Process zg�aszania ch�ci zmiany licznika ciep�a.
			 */
			MainProcess heatProcess = new MainProcess();
			List<ProcessPathElement> heatElements = heatProcess
					.getTemplatePathes();
			heatProcess.setActual(Boolean.FALSE);
			heatProcess
					.setProcessName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS);
			heatProcess
					.setDisplayName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							.getDisplayName());

			ProcessPathElement applyHeatProposal = new ProcessPathElement();
			applyHeatProposal.setActual(Boolean.FALSE);
			applyHeatProposal
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_APLLY");
			applyHeatProposal.setStatus(ProposalStatus.APPLIED);
			applyHeatProposal.setProcessTemplate(heatProcess);
			applyHeatProposal.setPlacement("Z\u0142o\u017Cono");
			applyHeatProposal.setOrderNumber(1);
			applyHeatProposal.setDisplayName("Z\u0142o\u017Cenie wniosku");
			// Dodajmy szablon pisma:
			ProposalData heatContent = new ProposalData();
			heatContent.setActual(Boolean.FALSE);
			StringBuilder heatBuilder = new StringBuilder();
			heatBuilder
					.append("Zwracam si\u0119 z uprzejm\u0105 pro\u015Bba o dokonanie wymiany miernik\u00F3w ciep\u0142a w mieszkaniu [poda\u0107 adres].\n\n");
			heatBuilder.append("Z powa\u017Caniem.\n");
			heatContent.setContent(heatBuilder.toString());
			heatContent.setMainProcess(heatProcess);
			applyHeatProposal.setContent(heatContent);
			applyHeatProposal
					.setResponsible(findByGroupName(GroupNames.CLIENT));
			heatElements.add(applyHeatProposal);

			// sekretariat analizuje
			officeProcess = new ProcessPathElement();
			officeProcess.setActual(Boolean.FALSE);
			officeProcess
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_PROCCESS");
			officeProcess.setStatus(ProposalStatus.PROCESSING);
			officeProcess.setProcessTemplate(heatProcess);
			officeProcess.setOrderNumber(2);
			officeProcess.setPlacement("Sekretariat");
			officeProcess
					.setDisplayName("Analiza wniosku - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(officeProcess);
			officeProcess.setResponsible(findByGroupName(GroupNames.OFFICE));

			ProcessPathElement technicalProcess = new ProcessPathElement();
			technicalProcess.setActual(Boolean.FALSE);
			technicalProcess
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_PROCCESS");
			technicalProcess.setStatus(ProposalStatus.ACCEPTED);
			technicalProcess.setProcessTemplate(heatProcess);
			technicalProcess.setOrderNumber(3);
			technicalProcess.setPlacement("Dzia\u0142 Techniczny");
			technicalProcess
					.setDisplayName("Zaakceptowano - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(technicalProcess);
			technicalProcess
					.setResponsible(findByGroupName(GroupNames.TECHNICAL_DEP));

			ProcessPathElement officeSuccess = new ProcessPathElement();
			officeSuccess.setActual(Boolean.FALSE);
			officeSuccess
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_REALIZED");
			officeSuccess.setStatus(ProposalStatus.REALIZED);
			officeSuccess.setProcessTemplate(heatProcess);
			officeSuccess.setOrderNumber(4);
			officeSuccess.setPlacement("Sekretariat");
			officeSuccess.setResponsible(findByGroupName(GroupNames.OFFICE));
			officeSuccess
					.setDisplayName("Zrealizowano - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(officeSuccess);

			ProcessPathElement officeHeatEnd = new ProcessPathElement();
			officeHeatEnd.setActual(Boolean.FALSE);
			officeHeatEnd
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_COMPLETED");
			officeHeatEnd.setStatus(ProposalStatus.COMPLETED);
			officeHeatEnd.setProcessTemplate(heatProcess);
			officeHeatEnd.setOrderNumber(5);
			officeHeatEnd.setPlacement("Powiadomienie klienta");
			officeHeatEnd
					.setDisplayName("Zrealizowano - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(officeHeatEnd);
			officeHeatEnd.setResponsible(findByGroupName(GroupNames.CLIENT));

			// odrzucanie
			ProcessPathElement officeHeatRefuse = new ProcessPathElement();
			officeHeatRefuse.setActual(Boolean.FALSE);
			officeHeatRefuse
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_REFUSED");
			officeHeatRefuse.setStatus(ProposalStatus.REFUSED);
			officeHeatRefuse.setProcessTemplate(heatProcess);
			officeHeatRefuse.setOrderNumber(2);
			officeHeatRefuse.setPlacement("Powiadomienie klienta");
			officeHeatRefuse
					.setDisplayName("Odrzucono - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(officeHeatRefuse);
			officeHeatRefuse.setResponsible(findByGroupName(GroupNames.CLIENT));

			// jesli szef lub ksiegowa:
			ProcessPathElement processHeatRefused = new ProcessPathElement();
			processHeatRefused.setActual(Boolean.FALSE);
			processHeatRefused
					.setProcessPathElementName(ProcessName.REQUEST_FOR_CHANGE_HEAT_METERS
							+ "_REFUSED");
			processHeatRefused.setStatus(ProposalStatus.REFUSED);
			processHeatRefused.setProcessTemplate(heatProcess);
			processHeatRefused.setOrderNumber(1);
			processHeatRefused.setPlacement("Powiadomienie klienta");
			processHeatRefused
					.setResponsible(findByGroupName(GroupNames.OFFICE));
			processHeatRefused
					.setDisplayName("Odrzucono - Pro\u015Bba o wymian\u0119 licznik\u00F3w ciep\u0142a");
			heatElements.add(processHeatRefused);

			applyHeatProposal.setNextStep(officeProcess);
			officeProcess.setNextStep(technicalProcess);
			officeProcess.setRefuseStep(officeHeatRefuse);
			technicalProcess.setPrevStep(officeProcess);
			technicalProcess.setRefuseStep(processHeatRefused);
			technicalProcess.setNextStep(officeSuccess);
			officeSuccess.setPrevStep(technicalProcess);
			officeSuccess.setNextStep(officeHeatEnd);
			processHeatRefused.setNextStep(officeHeatRefuse);

			heatProcess.setTemplatePathes(heatElements);
			entityManager.persist(heatProcess);

		}

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserBeanService#getUsersList(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersList(String login, String name, String surname)
			throws RemoteException {
		Query q = entityManager
				.createNamedQuery("getUsersWithPersonCountFiltered");
		q.setParameter("login", login + "%");
		q.setParameter("name", name + "%");
		q.setParameter("surname", surname + "%");
		Long count = (Long) q.getSingleResult();
		// Nie chcemy zwraca� nulla
		// zamiast tego zwracamy pust� list�.
		if (count == 0) {
			return null;
		}
		q = entityManager.createNamedQuery("getUsersWithPersonFiltered");
		q.setParameter("login", login + "%");
		q.setParameter("name", name + "%");
		q.setParameter("surname", surname + "%");

		return q.getResultList();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserBeanService#getUserById(java.lang.Long)
	 */
	@Override
	public User getUserById(Long id) throws RemoteException {
		Query q = entityManager.createNamedQuery("getUserByIdCount");
		q.setParameter("id", id);
		User result = null;
		Long count = (Long) q.getSingleResult();
		if (count == 1) {
			q = entityManager.createNamedQuery("getUserById");
			q.setParameter("id", id);
			result = (User) q.getSingleResult();
		}
		return result;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(User entity) throws RemoteException {
		entity = entityManager.merge(entity);
		Person p = entity.getPerson();
		p.setUser(null);
		entityManager.merge(p);
		entityManager.remove(entity);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserBeanService#getUSrByLogin(java.lang.String)
	 */
	@Override
	public User getUserByLogin(String login) throws RemoteException {
		User result = null;
		Query q = entityManager.createNamedQuery("getUserByLoginCount");
		q.setParameter("login", login);
		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			q = entityManager.createNamedQuery("getUserByLogin");
			q.setParameter("login", login);
			result = (User) q.getSingleResult();
		}
		return result;
	}

	/**
	 * Metoda pomocnicz do odnajywania grup.
	 * 
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	private BMSGroup findByGroupName(GroupNames name) throws RemoteException {
		Query q = entityManager.createNamedQuery("getGroupCountByName");
		q.setParameter("groupName", name);
		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			q = entityManager.createNamedQuery("getGroupByName");
			q.setParameter("groupName", name);
			return (BMSGroup) q.getSingleResult();
		}
		return null;
	}
}
