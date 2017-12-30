# ASCIIArt

The program was the 2nd bigger assignment at a Java programming course in the fall of 2016. The program takes the name of an ASCII graph text file as a command-line argument and reads the file into a 2D array. The program can be commanded to perform different things. The array can be printed as an ASCII graph or as signs that represent each ASCII character. Information about the size of the array and the number of times each character is present in the array can be requested. Graph can also be filtered with a median filter. Reset command resets the graph in its original form. Quit command ends the program.

## Examples
```
-------------------
| A S C I I A r t |
-------------------
printa/printi/info/filter [n]/reset/quit?
printa
*    *  #      *
   *    #*      
       ###   *  
 *    #####     
     o####o  *  
    ########    
  * o######o    
   ##########   
   o########o   
  ############  
  o##########o  
 ############## 
 o############o 
################
       ##       
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?
filter 5
printa/printi/info/filter [n]/reset/quit?
printa
*    *  #      *
   *    #*      
       ##*      
 *    ####*     
     o#####o    
    o######*    
    ########    
   *########o   
   ##########   
  o##########o  
  ############  
 ############## 
 o############o 
##o##########o##
       ##       
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?
reset
printa/printi/info/filter [n]/reset/quit?
printa
*    *  #      *
   *    #*      
       ###   *  
 *    #####     
     o####o  *  
    ########    
  * o######o    
   ##########   
   o########o   
  ############  
  o##########o  
 ############## 
 o############o 
################
       ##       
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?
printi
 6 15 15 15 15  6 15 15  0 15 15 15 15 15 15  6
15 15 15  6 15 15 15 15  0  6 15 15 15 15 15 15
15 15 15 15 15 15 15  0  0  0 15 15 15  6 15 15
15  6 15 15 15 15  0  0  0  0  0 15 15 15 15 15
15 15 15 15 15  7  0  0  0  0  7 15 15  6 15 15
15 15 15 15  0  0  0  0  0  0  0  0 15 15 15 15
15 15  6 15  7  0  0  0  0  0  0  7 15 15 15 15
15 15 15  0  0  0  0  0  0  0  0  0  0 15 15 15
15 15 15  7  0  0  0  0  0  0  0  0  7 15 15 15
15 15  0  0  0  0  0  0  0  0  0  0  0  0 15 15
15 15  7  0  0  0  0  0  0  0  0  0  0  7 15 15
15  0  0  0  0  0  0  0  0  0  0  0  0  0  0 15
15  7  0  0  0  0  0  0  0  0  0  0  0  0  7 15
 0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0
15 15 15 15 15 15 15  0  0 15 15 15 15 15 15 15
11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11
printa/printi/info/filter [n]/reset/quit?
quit
Bye, see you soon.
```

## Running

```
javac ASCIIArt.java
java ASCIIArt somefile.txt
```

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details

## Acknowledgments

In.java was provided by our teacher Jorma Laurikkala. It is a helper class that uses Scanner to read input from the user.
