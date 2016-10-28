/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie3;

import javax.microedition.midlet.*;	// Zawiera  definicje  klasy  MIDlet 
import javax.microedition.lcdui.*;	// Zawiera  definicję  dla  elementów  interfejsu  użytkownika
import java.util.*;		// Zawiera  definicję   obiektu  Random

public class Midlet extends MIDlet implements CommandListener
{

    Form form;				// Forma
    Display display;			// Wyświetlanie  na  ekranie
    TextField txtliczba;		// Pole  tekstowe  do  wyświetlania  informacji o  liczbie
    Command cmdCheck;                   // Polecenie  do  zatwierdzenia  przez  użytkownika
    Command cmdExit;                    // Polecenie  do  zatwierdzenia  przez  użytkownika
    int liczba;          // liczba
    int licznik;        // licznik
    
public void startApp()
{
    liczba =new Random().nextInt(101);		// Generowanie losowej  liczby
    licznik=0;					// Uruchomienie  licznika
    form=new Form("Midlet  Zgadywanka");	// Zainicjowanie  formy  z  tytułem
    display=Display.getDisplay(this);		// Wyświetlanie  na  ekranie
    txtliczba=new TextField("Wpisz  liczbę od  0  do  100)", "", 10, TextField.NUMERIC);  // Wprowadzenie  liczby przez  użytkownika
    cmdCheck=new Command("Sprawdź",Command.OK,1);	// Wybór  polecenia
    cmdExit=new Command("Wyjdź",Command.EXIT,3);	// Wybrór  polecenia
    	
    form.append(txtliczba);			// Dodanie  pola  tektowego  do  formy
    form.addCommand(cmdCheck);		// Polecenie  sprawdź
    form.addCommand(cmdExit);		// Polecenie  zakończenia  pracy  aplikacji
    
    form.setCommandListener(this);		// Dodanie  poleceń
    display.setCurrent(form);		// Wyświetlenie   formy
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
        if(c==cmdCheck)				// Sprawdź  czy zostało  wybrane  określone polecenie
        {
            if(txtliczba.getString().trim().length()==0)// Wybierz jeśli nie wpisano  danych  wejściowych
            {
                return;
            }
            Alert alert=null;
            int n=Integer.parseInt(txtliczba.getString());// Wprowadzenie  i  przeparsowanie  liczby  wpisanej  przez  użytkownika
            licznik++;					// zwiększenie stanu  licznika
            if(n< liczba)
            {
                alert=new Alert("Nie zgadłeś","Wpisz  większą  liczbę ",null,AlertType.WARNING);
		// Wyświetl  komunikat  jeśli  wpisałeś  liczbę  mniejszą niż  wylosowana
            }
            else if(n>liczba)
            {
                alert=new Alert("Nie  zgadłeś","Wpisz  mniejszą  liczbę ",null,AlertType.WARNING);
		// Wyświetl  komunikat  jeśli  wpisałeś  liczbę  większą niż  wylosowana
            }
            else
            {
                alert=new Alert("Trafiłeś"," Brawo  odgadnięte w "+ licznik +" ruchach.",null,AlertType.INFO);
			// Wyświetl  jeśli  użytkownik  wybrał  odpowiednią liczbę
			
                liczba=new Random().nextInt(101);	// Generowanie  losowej  liczby  
                licznik=0;			// Zresetuj  licznik
            }
            alert.setTimeout(Alert.FOREVER);   // Wyświetl  komunikat  w   przypadku  zakończenia  pracy  aplikacji  
            display.setCurrent(alert);	// Wyświetl  komunikat
        }
        if(c==cmdExit)      //  Sprawdź  czy zostało  wybrane  określone polecenie
        {
            destroyApp(true);		// Usuń  obiekt MIDlet jeśli  wybrałeś  polecenie EXIT
        }
        
    }
}