# Házi feladat specifikáció

Információk [itt](https://viauac00.github.io/laborok/hf)

## Mobil- és webes szoftverek
### 2022.10.23
### Initiative tracker
### Karakas Dávid - (PT8YWJ)
### karakasdavid2001@gmail.com 
### Laborvezető: Kapitány Erik

## Bemutatás

Az alkalmazást asztali szerepjátékokhoz ( például Dungeons and Dragons ) lehetne használni mint segédeszközt, amellyel az egyes játékos illetve nem játékos karakterek körben elhelyezkedő sorrendjét lehetne számon tartani.
Játék közben papíron vezetni ezt, sok helyet foglal és gyakran változik az állása, ami sok javítást igényel.
A program  játékosoknak és kalandmestereknek egyartánt hasznos lehet.

## Főbb funkciók

A főképernyőn lehetőség lesz választani a karakterek listája illetve a körszámláló közül.

Az alkalmazásban lehetőség van egy listában felvenni új karaktereket mint elemeket. Ezeknek meg lehet adni adatokat ( név, élet, kezdeményzési érték stb...). Az egyes elemeknél egy gomb segítségével dobunk egy 20-as kockával amely eldönti, hogy hanyadikok lesznek a sorban, majd hozzáadja a kezdeményezési értékét a karakternek.

A körszámlálóóban tudjuk nyomonkövetni, hogy melyik karakter következik éppen soron, ha az egyik befejezte a körét, akkor a sor végére kerül egy gomb használatával, továbbá az élet növelésére/csökkentésére lenn egy +/- gomb, amennyiben meg kell válni a karaktertől egy gomb nyomásával kivehetjük a kör listájából.


## Választott technológiák:

- Activity
- Fragmentek
- FloatinActionButton
- RecyclerView
- ROOM
