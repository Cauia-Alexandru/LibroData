/* Cauia Alexandru 324CB*/
                            LibroData

1.In cadrul acestui proiect am creat 10 clase si o interfata.
In fiecare clasa am creat variabelele necesare, constructori, get-eri, set-eri
si functiile de citire a detelor din fisiere, care le-am denumit "numele clasei"In.

2.In clasele(Book, EditorialGroup, PublishingBrand) care implementeaza interfata "IPublishingArtifacts" am creat si funtiile
Publish, care imi afiseaza datele despre acea clasa in format xml.

3.Urmatoarea parte a proiectului o constituie formarea de legaturi intre clase.
Pentru aceasta am creat clasa "Asocieri", unde pentru fiecare asociere am creat metode
aparte. Ideea fiecarei metode estea aproximativ aceeasi, difera parametrii care ii
primeste, in dependenta intre cine fac asocierea. Cu BufferReader citesc Id-urile din fisier,
le stochez in 2 int-uri, cu 2 for each caut obiectele de care am nevoie dupa acele 2
variabile in care am stocat Id-urile, apoi cu ajutorul functiei add"Obiect", adaug obiectul
intr-un array din instanta clasei care detine acel array.

4.Implementarea functionalitatilor se afla in clasa "Administration".
In primul rand intr-un constructor am apelat functiile de citire pentru fiecarea array
de tipul clasei corespunzatoare, apoi am apelat functiile care fac asocierile.

Prima metoda intoarce o lista de carti de la un retailer primind ca parametru IdRetailer.
Cu un for each caut retailer-ul si in stochez intr-o variabila. Apoi stochez intr-un array
obiectele care implementeaza interfata. Apoi inca cu un for each parcurg acele obiecete
verificand de ce timp sunt, accesez cartile si le pun in array.

Metoda 3, intoarce tarile in care poate fi distribuita o carte. Luand in consideratie ca o carte
poate sa o detina mai multi retaileri , cu un for each parcurg retailerii si cu 1 metoda
pot stoca cartile lor. Apoi prin acel array de carti, caut cartea si cu o metoda care imi intoarce
publishing retailerul dupa Id, pot accesa campul de countries. Verific ca limbile sa nu sa se
repete in array si le adaug, apoi returnez.

Metoda 4, intoarce cartile comune dintre 2 retaileri. Cu ajutorul primei metode stochez cartile
lor. Apoi cu 2 for-uri imbricate parcurg cele 2 array-uri de carti, verific daca referintele sunt
egale si le pun in alt array.

Celelalte metode sunt elaborate aproximativ dupa aceeasi strategie.

5. In clasa "Teste" am apelat fiecare metoda de 5 ori pentru Id-uri diferite pentru a testa functionalitatea proiectului