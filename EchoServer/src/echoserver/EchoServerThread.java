package echoserver;

import java.net.*;
import java.io.*;
import java.util.*;


class users {

  String Name;
  String Surname;
  long PESEL;
  int AccNum;
  double Balance;

}



public class EchoServerThread implements Runnable {
  protected Socket socket;

  public EchoServerThread(Socket clientSocket) {
    this.socket = clientSocket;
  }

  public void run() {
    //Deklaracje zmiennych
    BufferedReader brinp = null;
    DataOutputStream out = null;
    String threadName = Thread.currentThread().getName();

    //inicjalizacja strumieni
    try {
      brinp = new BufferedReader(
              new InputStreamReader(
                      socket.getInputStream()
              )
      );
      out = new DataOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      System.out.println(threadName + "| Błąd przy tworzeniu strumieni " + e);
      return;
    }
    String line = null;

    // ///////////////////////////
    Scanner scanner = null;


    int l = 1;



    String login = "Podaj swoj login";
    try {
      out.writeBytes(login + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      login = brinp.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(login);

    //pętla główna
    while (true) {
      try {
          
        try {
           scanner = new Scanner(new File("users.txt"));
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }
        
/*            ArrayList<users> users = new ArrayList<>();

            while (scanner.hasNext()) {

              users user = new users();

              user.Name = scanner.next();
              user.Surname = scanner.next();
              user.PESEL = scanner.nextLong();
              user.AccNum = scanner.nextInt();
              user.Balance = Double.parseDouble((scanner.next()));

              users.add(user);
            } */
            
        if ("Admin".equals(login)) {
            
            ArrayList<users> users = new ArrayList<>();

            while (scanner.hasNext()) {

              users user = new users();

              user.Name = scanner.next();
              user.Surname = scanner.next();
              user.PESEL = scanner.nextLong();
              user.AccNum = scanner.nextInt();
              user.Balance = Double.parseDouble((scanner.next()));

              users.add(user);
            }  
            
//            Terminal Bankiera z możliwością edycji danych klientów
          String wtd = "Witaj w panelu administacyjnym. Co chcesz zrobic? | Wybierz 1 aby dodac nowe konto | Wybierz 2 aby edytowac istniejace konto";
          out.writeBytes(wtd + "\n\r");
          out.flush();
          String read_wtd = brinp.readLine();
          if("1".equals(read_wtd)){
              users user = new users();

              String imie = "Imie:";
              out.writeBytes(imie + "\n\r");
              out.flush();
              System.out.println(imie);
              String read_imie = brinp.readLine();
              user.Name = read_imie;

              String nazwisko = "Nazwisko:";
              out.writeBytes(nazwisko + "\n\r");
              out.flush();
              System.out.println(nazwisko);
              String read_nazwisko = brinp.readLine();
              user.Surname = read_nazwisko;

              String pesel = ("Nr pesel:");
              out.writeBytes(pesel + "\n\r");
              out.flush();
              System.out.println(pesel);
              String read_pesel = brinp.readLine();
              user.PESEL = Long.parseLong(read_pesel);

              String konto = ("Nr konta: ");
              out.writeBytes(konto + "\n\r");
              out.flush();
              System.out.println(konto);
              String read_konto = brinp.readLine();
              user.AccNum = Integer.parseInt(read_konto);

              String stan = ("Kwota poczatkowa: ");
              out.writeBytes(stan + "\n\r");
              out.flush();
              System.out.println(stan);
              String read_stan = brinp.readLine();
              user.Balance = Double.parseDouble(read_stan);

              users.add(user);
              FileOutputStream stream = new FileOutputStream("users.txt", false);
              PrintWriter save = new PrintWriter(stream);

              for (int i = 0; i < users.size(); i++) {
                save.print(users.get(i).Name + " ");
                save.print(users.get(i).Surname + " ");
                save.print(users.get(i).PESEL + " ");
                save.print(users.get(i).AccNum + " ");
                save.println(users.get(i).Balance + " ");
              }
              save.close();
          }
          else if ("2".equals(read_wtd)) {
            String podaj_nr_konta = "Podaj nr konta:";
            out.writeBytes(podaj_nr_konta + "\n\r");
            out.flush();
            System.out.println(podaj_nr_konta);
            String read_podajNr = brinp.readLine();
            int AccNumb = Integer.parseInt(read_podajNr);
            for (int i = 0; i < users.size(); i++) {
              if (users.get(i).AccNum == AccNumb) {
                String tmpI = users.get(i).Name;
                String newImie = "Imie:";
                out.writeBytes(newImie + "\n\r");
                out.flush();
                System.out.println(newImie);
                tmpI = brinp.readLine();
                users.get(i).Name = tmpI;

                String tmpN = users.get(i).Surname;
                String newNazwisko = "Nazwisko:";
                out.writeBytes(newNazwisko + "\n\r");
                out.flush();
                System.out.println(newNazwisko);
                tmpN = brinp.readLine();
                users.get(i).Surname = tmpN;

                long tmpP = users.get(i).PESEL;
                String newPesel = "Pesel:";
                out.writeBytes(newPesel + "\n\r");
                out.flush();
                System.out.println(newPesel);
                String readNewPesel = brinp.readLine();
                tmpP = Long.parseLong(readNewPesel);
                users.get(i).PESEL = tmpP;
                break;
              }

            }
            }
          FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close();

        } else {
            
            ArrayList<users> users = new ArrayList<>();

            while (scanner.hasNext()) {

              users user = new users();

              user.Name = scanner.next();
              user.Surname = scanner.next();
              user.PESEL = scanner.nextLong();
            user.AccNum = scanner.nextInt();
            user.Balance = Double.parseDouble((scanner.next()));

            users.add(user);
          }

          String accnum = "Podaj nr.konta";
          out.writeBytes(accnum + "\n\r");
          out.flush();
          accnum = brinp.readLine();

          String tast = "Co chcesz zrobic: 1. Sprawdzic stan konta 2. Wyplacic srodki 3. Wplacic srodki  4. zrobic przelew ";
          out.writeBytes(tast + "\n\r");
          out.flush();
          System.out.println(tast);
          String read_tast = brinp.readLine();

          System.out.println(accnum);
          int AccNumb = Integer.parseInt(accnum);

          if ("1".equals(read_tast)) {

            for (int i = 0; i < users.size(); i++) {

              if (users.get(i).AccNum == AccNumb & users.get(i).Name.equals(login)) {
                System.out.println("Stan konta " + users.get(i).Balance);
                String money = "Stan konta " + users.get(i).Balance;
                out.writeBytes(money + "\n");
                out.flush();
                break;
                 }
            }
            
                      FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close();
          
          } else if ("2".equals(read_tast)) {

            String moneyT = "Ile pieniedzy wyplacic?";
            out.writeBytes(moneyT + '\n');
            out.flush();
            moneyT = brinp.readLine();

            double toWithdraw = Double.parseDouble(moneyT);

            for (int i = 0; i < users.size(); i++) {

              if (users.get(i).AccNum == AccNumb & users.get(i).Name.equals(login) & users.get(i).Balance >= toWithdraw) {
                double tmp = users.get(i).Balance;
                tmp = tmp - toWithdraw;
                users.get(i).Balance = tmp;
                break;

                                
              }
            }
            
          FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close();
            

          } else if ("3".equals(read_tast)) {

            String moneyF = "Ile pieniedzy wplacic?";
            out.writeBytes(moneyF + '\n');
            out.flush();
            moneyF = brinp.readLine();

            double toDeposit = Double.parseDouble(moneyF);

            for (int i = 0; i < users.size(); i++) {

              if (users.get(i).AccNum == AccNumb & users.get(i).Name.equals(login) ) {
                double tmp = users.get(i).Balance;
                tmp = tmp + toDeposit;
                users.get(i).Balance = tmp;
                break;
              } 
            }
            
            
          FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close();


          } else if ("4".equals(read_tast)) {

            String przelew = "Ile pieniedzy przelac?";
            out.writeBytes(przelew + "\n\r");
            out.flush();
            przelew = brinp.readLine();
            String komu = "Nr konta na ktore chcesz przelac pieniadze: ";
            out.writeBytes(komu + "\n\r");
            out.flush();
            komu = brinp.readLine();

            int To = Integer.parseInt(komu);
            double transfer = Double.parseDouble(przelew);

            for (int i = 0; i < users.size(); i++) {
//              if (users.get(i).AccNum == To){
//                if (To == AccNumb){
//                  System.out.println("Probujesz przelac pieniadze samemu sobie")}
//                else if (users.get(i).AccNum != To){ System.out.println("Nie ma takiego konta") }
//              else{}
              if (users.get(i).AccNum == AccNumb & users.get(i).Name.equals(login) & users.get(i).Balance >= transfer) {
                double tmp = users.get(i).Balance;
                tmp = tmp - transfer;
                users.get(i).Balance = tmp;
              } else if (users.get(i).AccNum == To) {
                double tmp = users.get(i).Balance;
                tmp = tmp + transfer;
                users.get(i).Balance = tmp;
              } 
             


            }
            
            
                      FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close();

          } 

/*          FileOutputStream stream = new FileOutputStream("users.txt", false);
          PrintWriter save = new PrintWriter(stream);

          for (int i = 0; i < users.size(); i++) {
            save.print(users.get(i).Name + " ");
            save.print(users.get(i).Surname + " ");
            save.print(users.get(i).PESEL + " ");
            save.print(users.get(i).AccNum + " ");
            save.println(users.get(i).Balance + " ");
          }
          save.close(); */


          try {
            System.out.println("Odczytano linię: " + read_tast);
            if (read_tast == null || "quit".equals(read_tast)) {
              scanner.close();
//              save.close();
              socket.close();
              System.out.println("Zakończenie pracy z klientem...");
              break;
            }
          } catch (IOException e) {
            System.out.println("Błąd wejścia-wyjścia: " + e);
            return;

          }
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}