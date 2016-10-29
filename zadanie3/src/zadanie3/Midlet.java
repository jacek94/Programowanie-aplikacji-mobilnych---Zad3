/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie3;

import javax.microedition.midlet.*;	// Zawiera  definicje  klasy  MIDlet 
import javax.microedition.lcdui.*;	// Zawiera  definicj�  dla  element�w  interfejsu  u�ytkownika
import java.util.*;		// Zawiera  definicj�   obiektu  Random

public class Midlet extends MIDlet implements CommandListener
{

    Form form;				// Forma
    Display display;			// Wy�wietlanie  na  ekranie
    TextField txtliczba;		// Pole  tekstowe  do  wy�wietlania  informacji o  liczbie
    Command cmdCheck;                   // Polecenie  do  zatwierdzenia  przez  u�ytkownika
    Command cmdExit;                    // Polecenie  do  zatwierdzenia  przez  u�ytkownika
    int liczba;          // liczba
    int licznik;        // licznik
    
public void startApp()
{
    liczba =new Random().nextInt(101);		// Generowanie losowej  liczby
    licznik=0;					// Uruchomienie  licznika
    form=new Form("Midlet  Zgadywanka");	// Zainicjowanie  formy  z  tytu�em
    display=Display.getDisplay(this);		// Wy�wietlanie  na  ekranie
    txtliczba=new TextField("Wpisz  liczb� od  0  do  100)", "", 10, TextField.NUMERIC);  // Wprowadzenie  liczby przez  u�ytkownika
    cmdCheck=new Command("Sprawd�",Command.OK,1);	// Wyb�r  polecenia
    cmdExit=new Command("Wyjd�",Command.EXIT,3);	// Wybr�r  polecenia
    	
    form.append(txtliczba);			// Dodanie  pola  tektowego  do  formy
    form.addCommand(cmdCheck);		// Polecenie  sprawd�
    form.addCommand(cmdExit);		// Polecenie  zako�czenia  pracy  aplikacji
    
    form.setCommandListener(this);		// Dodanie  polece�
    display.setCurrent(form);		// Wy�wietlenie   formy
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
        if(c==cmdCheck)				// Sprawd�  czy zosta�o  wybrane  okre�lone polecenie
        {
            if(txtliczba.getString().trim().length()==0)// Wybierz je�li nie wpisano  danych  wej�ciowych
            {
                return;
            }
            Alert alert=null;
            int n=Integer.parseInt(txtliczba.getString());// Zatwierdzenie  wylosowanej  liczby (liczba  ukryta przed u�ytkownikiem)  
            licznik++;					// zwi�kszenie stanu  licznika
            if(n< liczba)
            {
                alert=new Alert("Nie zgad�e�","Wpisz  wi�ksz�  liczb� ",null,AlertType.WARNING);
		// Wy�wietl  komunikat  je�li  u�ytkownik  wybra�  liczb�  mniejsz� ni�  wylosowana
            }
            else if(n>liczba)
            {
                alert=new Alert("Nie  zgad�e�","Wpisz  mniejsz�  liczb� ",null,AlertType.WARNING);
		// Wy�wietl  komunikat  je�li  u�ytkownik wybra�  liczb�  wi�ksz� ni�  wylosowana
            }
            else
            {
                alert=new Alert("Trafi�e�"," Brawo  odgadni�te w "+ licznik +" ruchach.",null,AlertType.INFO);
			// Wy�wietl komunikat je�li  u�ytkownik  wybra�  odpowiedni� liczb�
			
                liczba=new Random().nextInt(101);// Generowanie  losowej  liczby  
                licznik=0;			// Zresetuj  licznik
            }
            alert.setTimeout(Alert.FOREVER); // Wy�wietl  komunikat  w   przypadku  zako�czenia  pracy  aplikacji  
            display.setCurrent(alert);	// Wy�wietl  komunikat
        }
        if(c==cmdExit)      //  Sprawd�  czy zosta�o  wybrane  okre�lone polecenie
        {
            destroyApp(true);		// Usu�  obiekt MIDlet je�li  wybra�e�  polecenie EXIT
        }
        
    }
}