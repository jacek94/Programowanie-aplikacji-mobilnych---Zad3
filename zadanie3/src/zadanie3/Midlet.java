/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie3;

import javax.microedition.midlet.*;	// Zawiera  definicje  klasy  MIDlet 
import javax.microedition.lcdui.*;	// Zawiera  definicjê  dla  elementów  interfejsu  u¿ytkownika
import java.util.*;		// Zawiera  definicjê   obiektu  Random

public class Midlet extends MIDlet implements CommandListener
{

    Form form;				// Forma
    Display display;			// Wyœwietlanie  na  ekranie
    TextField txtliczba;		// Pole  tekstowe  do  wyœwietlania  informacji o  liczbie
    Command cmdCheck;                   // Polecenie  do  zatwierdzenia  przez  u¿ytkownika
    Command cmdExit;                    // Polecenie  do  zatwierdzenia  przez  u¿ytkownika
    int liczba;          // liczba
    int licznik;        // licznik
    
public void startApp()
{
    liczba =new Random().nextInt(101);		// Generowanie losowej  liczby
    licznik=0;					// Uruchomienie  licznika
    form=new Form("Midlet  Zgadywanka");	// Zainicjowanie  formy  z  tytu³em
    display=Display.getDisplay(this);		// Wyœwietlanie  na  ekranie
    txtliczba=new TextField("Wpisz  liczbê od  0  do  100)", "", 10, TextField.NUMERIC);  // Wprowadzenie  liczby przez  u¿ytkownika
    cmdCheck=new Command("SprawdŸ",Command.OK,1);	// Wybór  polecenia
    cmdExit=new Command("WyjdŸ",Command.EXIT,3);	// Wybrór  polecenia
    	
    form.append(txtliczba);			// Dodanie  pola  tektowego  do  formy
    form.addCommand(cmdCheck);		// Polecenie  sprawdŸ
    form.addCommand(cmdExit);		// Polecenie  zakoñczenia  pracy  aplikacji
    
    form.setCommandListener(this);		// Dodanie  poleceñ
    display.setCurrent(form);		// Wyœwietlenie   formy
}


public void pauseApp()
{
}

public void destroyApp(boolean unconditional)
{
    notifyDestroyed();
}

public void commandAction(Command c,Displayable d)
    {
        if(c==cmdCheck)				// SprawdŸ  czy zosta³o  wybrane  okreœlone polecenie
        {
            if(txtliczba.getString().trim().length()==0)// Wybierz jeœli nie wpisano  danych  wejœciowych
            {
                return;
            }
            Alert alert=null;
            int n=Integer.parseInt(txtliczba.getString());// Zatwierdzenie  wylosowanej  liczby (liczba  ukryta przed uŸytkownikiem)  
            licznik++;					// zwiêkszenie stanu  licznika
            if(n< liczba)
            {
                alert=new Alert("Nie zgad³eœ","Wpisz  wiêksz¹  liczbê ",null,AlertType.WARNING);
		// Wyœwietl  komunikat  jeœli  u¿ytkownik  wybra³  liczbê  mniejsz¹ ni¿  wylosowana
            }
            else if(n>liczba)
            {
                alert=new Alert("Nie  zgad³eœ","Wpisz  mniejsz¹  liczbê ",null,AlertType.WARNING);
		// Wyœwietl  komunikat  jeœli  u¿ytkownik wybra³  liczbê  wiêksz¹ ni¿  wylosowana
            }
            else
            {
                alert=new Alert("Trafi³eœ"," Brawo  odgadniête w "+ licznik +" ruchach.",null,AlertType.INFO);
			// Wyœwietl komunikat jeœli  u¿ytkownik  wybra³  odpowiedni¹ liczbê
			
                liczba=new Random().nextInt(101);// Generowanie  losowej  liczby  
                licznik=0;			// Zresetuj  licznik
            }
            alert.setTimeout(Alert.FOREVER); // Wyœwietl  komunikat  w   przypadku  zakoñczenia  pracy  aplikacji  
            display.setCurrent(alert);	// Wyœwietl  komunikat
        }
        if(c==cmdExit)      //  SprawdŸ  czy zosta³o  wybrane  okreœlone polecenie
        {
            destroyApp(true);		// Usuñ  obiekt MIDlet jeœli  wybra³eœ  polecenie EXIT
        }
        
    }
}