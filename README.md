# equation-solver
Able to solve basic set of linear equation using gauss elimination technique

## EquationList.java
Method:

###### addEquation(string equation)
Menambahkan sebuah persamaan ke list persamaan yang terdapat dalam EquationList objek. Persamaan yang diberikan bentuknya bebas namun disarankan untuk memiliki bentuk seperti ini 5x + -6y = 3 jika pada persamaan tersebut terdapat tanda minus.

###### createMatrix()
Menghasilkan sebuah matriks dari kumpulan persamaan yang tersimpan dalam variabel pada EquationList objek. Matriks yang dihasilkan akan beruba augmented matriks dimana nantinya matriks ini dapat digunakan untuk menyelesaikan persamaan dengan menggunakan teknik eliminasi gauss

###### writeMatrix()
Menuliskan matriks yang terdapat dalam objek EquationList dalam format sebagai berikut
a b c x  
d e f y  
g e h z  

###### toMatrix()
Metode yang digunakan untuk mendapatkan nilai matriks yang terdapat dalam objek EquationList

###### removeSpace(string equation)
Metode ini digunakan untuk menyamakan format dari masukan sehingga dapat diproses oleh program.

###### parseCoefficient()
Mengambil tiap-tiap koefisien dari variabel pada persamaan yang ada dalam objek EquationList. Koefisien inilah yang kemudian akan dimasukan ke dalam matriksnya


## ReadFile.java
Method:

##### readEquationFrom(String filename, EquationList equationList)
Membaca sebuah file txt yang berisi sistem persamaan linear  
Contoh file: test.txt  
2x + 1y = 3  
1x + 1y = 2  


## WriteFile.java
Method:

##### writeResult(String filename, Matrix matrix)
Menyimpan hasil solusi sistem persamaan ke dalam suatu file txt
