# BuildingManager

## Opis  
To repo służy do migracji starego systemu obiegu dokumentów, tworzonego na potrzeby pracy magisterskiej, na nową technologię.\
Celem głownym jest samodoskonalenie i nauka nowych technologii.\
\
Temat Pracy:\
System obiegu dokumentów w rozproszonej architekturze\
trójwarstwowej z wykorzystaniem technologii Java 2 Enterprise\
Edition\
**Data: 2012**\
\
Technologie:\
Backend:\
Server 1:\
  Ubuntu Server 10.04 + load balaner\
  Jboss 4.2.2\
  Java 1.6\
  EJB 3.0\
Server 2 i 3:\
 Ms Windows XP\
  Jboss 4.2.2\
  Java 1.6\
  EJB 3.0\
\
Baza danych:\
  Postres SQL: 8.3\
\
Web Client:\
  Spring Framewrok 3.2\
  Apache Tomcat 6.0.20\
  JSP\
  Apache Struts 3.0.2\
  DisplayTag 1.6\

## 23.01.2020
Stworzyłem Parent Poma, Dodałem mavena do projektu ServerApplication,\
usunałem stare pliki eclipse'a

## 27.01.2020

Przed pierwszym commitem trzeba było usunąć pliki katalogi ".svn",\
 które były w każdym katalogu i pakiecie:\
 Skrypt w pythonie:
 
 ```python
import os, sys, shutil
# Czyszczenie starych plików .svn
def searchAndRemove(dir_list):
    print("searchAndRemove")
    for dirEntry in dir_list:
        if (dirEntry.name == ".svn"):
            shutil.rmtree(dirEntry)
            print("dir:" + dirEntry.path + " removed")
        elif (dirEntry.is_dir()):
            searchAndRemove(os.scandir(dirEntry.path))


path = input(sys.argv)
print("path:" + path)
dir_iterator = os.scandir(path)
searchAndRemove(dir_iterator)
```
