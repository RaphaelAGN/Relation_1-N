
import java.util.Scanner;
import java.io.*;

//Classe View que contem os menus
public class CRUD{
   public static void main(String[] args) throws IOException {
      Controle ct = new Controle("filmes.db");
      Controle ctg = new Controle("generos.db");
      Controle index = new Controle("index.db");
   
      Scanner s = new Scanner(System.in);
      
      int escolha = -1;
      
      while(escolha != 0){
         System.out.println("---------------------------------\nMain Menu:\n"+
               	"0 - Sair;\n"+
               	"1 - Menu de Generos;\n"+
               	"2 - Menu de Filmes\n------------------------------------");
         escolha = s.nextInt();
         if(escolha == 1){
            while(escolha != -1){
               String menu1 = "\nMenu de opcoes de generos: \n0. Voltar para o main menu. \n1. Adicionar Genero\n2. Alterar Genero\n3. Excluir Genero\n4. Busca\n";
            
               System.out.println(menu1);
               System.out.print("O que deseja fazer: ");
               escolha = s.nextInt();
            
               switch(escolha){
                  case 0:
                     escolha = -1;
                     break;
                  
                  case 1:
                     adicionarGenero(ctg, index);
                     break;
               
                  case 2:
                     alterarGenero(ct, ctg);
                     break;
               
                  case 3:
                     excluirGenero(ctg);
                     break;
               
                  case 4:
                     buscarGenero(ctg);
                     break;
               
                  default:
                     System.out.print("\nNenhuma das opcoes foi escolhida. Digite apenas numeros de 0 a 4.\n");
               }
            }//fim while generos
         }else if(escolha == 2){
         
            while(escolha != -1){
               String menu2 = "\nMenu de opcoes de filmes: \n0. Voltar para o main menu. \n1. Adicionar Filme\n2. Alterar Filme\n3. Excluir Filme\n4. Busca\n";
            
               System.out.println(menu2);
               System.out.print("O que deseja fazer: ");
               escolha = s.nextInt();
               switch(escolha){
                  case 0:
                     escolha = -1;
                     break;
                     
                  case 1:
                     adicionar(ct, ctg);
                     break;
               
                  case 2:
                     alterar(ct, ctg);
                     break;
               
                  case 3:
                     excluir(ct);
                     break;
               
                  case 4:
                     buscar(ct, ctg);
                     break;
                  
                  default:
                     System.out.print("\nNenhuma das opcoes foi escolhida. Digite apenas numeros de 0 a 4.\n");
               }
            }//fim while filmes
         }
      }
     
      System.out.print("\nProcesso encerrado.");
               
   }//end main

   /**
    * Metodo de exclusao de filmes
   **/
   public static void excluir(Controle ct){
      Scanner s = new Scanner(System.in);
      int id;
   
      System.out.print("\nDigite a ID do filme a ser excluido: ");
      id = s.nextInt();
   
      long pos = ct.busca(id);//utiliza o metodo de busca para pegar a posicao do filme a ser buscado caso o mesmo exista
   
      if(pos == -1)
         System.out.println("\nRegistro nao encontrado.");
      else{
         System.out.print("\nTem certeza que deseja excluir?[s/n]: ");
         switch(s.next().charAt(0)){
            case 's': case 'S':
               ct.excluir(pos);
               System.out.println("\nRegistro excluido com sucesso.");
               break;
            case 'n': case 'N':
               System.out.println("\nExclusao cancelada.");
         }
      }
   }//end excluir
   
   /**
    * Metodo de exclusao de generos
   **/
   public static void excluirGenero(Controle ctg){
      Scanner s = new Scanner(System.in);
      int id;
   
      System.out.print("\nDigite a ID do genero a ser excluido: ");
      id = s.nextInt();
   
      long pos = ctg.busca(id);//utiliza o metodo de busca para pegar a posicao do filme a ser buscado caso o mesmo exista
   
      if(pos == -1)
         System.out.println("\nRegistro nao encontrado.");
      else{
         System.out.print("\nTem certeza que deseja excluir?[s/n]: ");
         switch(s.next().charAt(0)){
            case 's': case 'S':
               ctg.excluir(pos);
               System.out.println("\nRegistro excluido com sucesso.");
               break;
            case 'n': case 'N':
               System.out.println("\nExclusao cancelada.");
         }
      }
   }//end excluir
   /**
    * Metodo de adicao de filmes
   **/
   public static void adicionar(Controle ct, Controle ctg) throws IOException {
      Scanner s = new Scanner(System.in);
      System.out.print("\nDigite as informacoes do filme a ser adicionado.\n");
      boolean exist;
      System.out.print("\nTitulo: ");
      Filme f = new Filme(s.nextLine());
   
      System.out.print("\nTitulo Original: ");
      f.setTituloOri(s.nextLine());
   
      System.out.print("\nPais de Origem: ");
      f.setPais(s.nextLine());
      
      System.out.print("\nAno de lancamento: ");
      f.setAno(s.nextShort());
   
      System.out.print("\nDuracao em minutos: ");
      f.setDuracao(s.nextShort());
      s.nextLine();
   
      System.out.print("\nDiretor: ");
      f.setDiretor(s.nextLine());
   
      System.out.print("\nSinopse: ");
      f.setSinopse(s.nextLine());
      
      System.out.print("\nID do genero(utilize numeros): ");
      int idGenero = s.nextInt();
      exist = ctg.IDconfirmation(idGenero);
      while(!exist){
         System.out.print("\nFavor digitar novamente: ");
         System.out.print("\nID do genero(utilize numeros): ");
         idGenero = s.nextInt();
         exist = ctg.IDconfirmation(idGenero);   
      }
      System.out.print("Tem certeza que deseja esse genero? [s/n]: ");
      char confirm = s.next().charAt(0);
      switch(confirm){
         case 'n': case 'N':
            while(confirm == 'n' || confirm == 'N'){
               System.out.print("ID do genero do filme (digite numeros): ");
               idGenero = s.nextInt();
               exist = ctg.IDconfirmation(idGenero);
               while(!exist){
                  System.out.print("\nFavor digitar novamente: ");
                  System.out.print("\nID do genero(utilize numeros): ");
                  idGenero = s.nextInt();
                  exist = ctg.IDconfirmation(idGenero);
               }
               System.out.print("Tem certeza que deseja esse genero? [s/n]: ");
               confirm = s.next().charAt(0);
            }
            break;
         case 's': case 'S':
            System.out.print("\nID adicionada com sucesso");
      }
      f.setIDGenero(idGenero);
      
      System.out.print("\nTem certeza que deseja adicionar o filme?[s/n]: ");
      switch(s.next().charAt(0)){
         case 's': case 'S':
            f.setIDFilme(ct.novoID());
            byte[] vet = f.getByteArray();
            ct.vaiPFim();
            ct.incluir(' ', vet);
            System.out.println("\nFilme adicionado com sucesso!\n");
            break;
         case 'n': case 'N':
            System.out.println("\nAdicao cancelada.\n");
      
      }
   }//end adicionar
   
   
   /**
    * Metodo de adicao de generos
   **/
   public static void adicionarGenero(Controle ctg, Controle index) throws IOException {
      Scanner s = new Scanner(System.in);
      Index i = new Index();
      System.out.print("\nDigite as informacoes do genero a ser adicionado.\n");
   
      System.out.print("\nNome do genero: ");
      Genero g = new Genero(s.nextLine());
      
      System.out.print("\nTem certeza que deseja adicionar o genero?[s/n]: ");
      switch(s.next().charAt(0)){
         case 's': case 'S':
            g.setIDGenero(ctg.novoID());
            i.setID(index.novoID());
            byte[] vet = g.getByteArray();
            byte[] vetI = i.getByteArray();
            ctg.vaiPFim();
            ctg.incluir(' ', vet);
            index.vaiPFim();
            index.incluir(' ', vetI);
            System.out.println("\nGenero adicionado com sucesso!\n");
            break;
         case 'n': case 'N':
            System.out.println("\nAdicao cancelada.\n");
      
      }
   }//end adicionar
   
   /**
    * Metodo de busca para exclusao e pesquisa de generos
    * @param instancia da classe controleGeneros
   **/
   public static void buscarGenero(Controle ctg){
      Scanner s = new Scanner(System.in);
      System.out.print("\nDigite a id, do genero, a ser buscada: ");
      int id = s.nextInt();
      ctg.mostrarGenero(id);
   }//end buscar
   
   /**
    * Metodo de busca para exclusao e pesquisa de filmes
   **/
   public static void buscar(Controle ct, Controle ctg){
      Scanner s = new Scanner(System.in);
      //boolean exist;
      System.out.print("\nDigite a id do filme a ser buscado: ");
      int id = s.nextInt();
      int idGenero = ct.mostrarFilme(id);
      ctg.mostrarGenero(idGenero);
   }//end buscar

   /**
    * Metodo de alteracao de filmes
   **/
   public static void alterar(Controle ct, Controle ctg){
      Scanner s = new Scanner(System.in);
      int id;
      boolean exist;
      System.out.print("\nDigite a ID do filme a ser alterado: ");
      id = s.nextInt();
      long pos = ct.busca(id);
      Filme f = new Filme();
      if(pos == -1)
         System.out.println("\nRegistro nao encontrado.");
      else{
         System.out.print("\nTem certeza que deseja alterar? (A alteracao resultara na exclusao dos dados atuais) [s/n]:  ");
         switch(s.next().charAt(0)){
            case 's': case 'S':
               
               ct.excluir(pos);
               System.out.println("\nRegistro excluido com sucesso");
               try{
                  s.nextLine();
                  System.out.print("\nDigite as informacoes do filme a ser adicionado.\n");
               
                  System.out.print("\nTitulo: ");
                  f.setTitulo(s.nextLine());
               
                  System.out.print("\nTitulo Original: ");
                  f.setTituloOri(s.nextLine());
                  System.out.print("\nPais de Origem: ");
                  f.setPais(s.nextLine());
               
                  System.out.print("\nAno de lancamento: ");
                  f.setAno(s.nextShort());
               
                  System.out.print("\nDuracao em minutos: ");
                  f.setDuracao(s.nextShort());
                  s.nextLine();
               
                  System.out.print("\nDiretor: ");
                  f.setDiretor(s.nextLine());
               
                  System.out.print("\nSinopse: ");
                  f.setSinopse(s.nextLine());
                  
                  System.out.print("\nID do genero(utilize numeros): ");
                  int idGenero = s.nextInt();
                  exist = ctg.IDconfirmation(idGenero);
                  while(!exist){
                     System.out.print("\nFavor digitar novamente: ");
                     System.out.print("\nID do genero(utilize numeros): ");
                     idGenero = s.nextInt();
                     exist = ctg.IDconfirmation(idGenero);   
                  }
                  System.out.print("Tem certeza que deseja esse genero? [s/n]: ");
                  char confirm = s.next().charAt(0);
                  switch(confirm){
                     case 'n': case 'N':
                        while(confirm == 'n' || confirm == 'N'){
                           System.out.print("ID do genero do filme (digite numeros): ");
                           idGenero = s.nextInt();
                           exist = ctg.IDconfirmation(idGenero);
                           while(!exist){
                              System.out.print("\nFavor digitar novamente: ");
                              System.out.print("\nID do genero(utilize numeros): ");
                              idGenero = s.nextInt();
                              exist = ctg.IDconfirmation(idGenero);   
                           }
                           System.out.print("Tem certeza que deseja esse genero? [s/n]: ");
                           confirm = s.next().charAt(0);
                        }
                        break;
                     case 's': case 'S':
                        System.out.print("\nID alterada com sucesso");
                        f.setIDGenero(idGenero);
                  }
                  
                  f.setIDFilme(id);
                  byte[] vet = f.getByteArray();
                  ct.vaiPFim();
                  ct.incluir(' ', vet);
                  System.out.print("\nFilme alterado com sucesso!\n");
                        
               }catch(IOException E){
                  E.printStackTrace();
               }
               break;
            case 'n': case 'N':
               System.out.print("\nAlteracao cancelada.");
         }
      }
   }//end alterar
   
   /**
    * Metodo de alteracao de generos
   **/   
   public static void alterarGenero(Controle ct, Controle ctg){
      Scanner s = new Scanner(System.in);
      int idGenero;
      Genero g = new Genero();
      System.out.print("\nDigite a id do genero a ser alterado: ");
      idGenero = s.nextInt();
      long pos = ctg.busca(idGenero);
      if(pos == -1){
         System.out.print("\nGenero nao encontrado.\n");
      }else{
         System.out.print("\nTem certeza que deseja alterar? (A alteracao resultara na exclusao dos dados atuais) [s/n]:  ");
         switch(s.next().charAt(0)){
            case 's': case 'S':
               ctg.excluir(pos);
               System.out.println("\nRegistro excluido com sucesso");
               try{
                  s.nextLine();
                  System.out.print("\nDigite as informacoes do genero a ser adicionado.\n");
               
                  System.out.print("\nNome do genero: ");
                  g.setNomeGenero(s.nextLine());
                  
                  g.setIDGenero(idGenero);
                  byte[] vet = g.getByteArray();
                  ctg.vaiPFim();
                  ctg.incluir(' ', vet);
                  System.out.print("\nGenero alterado com sucesso!\n");
               }catch(IOException E){
                  E.printStackTrace();
               }
               break;
            case 'n': case 'N':
               System.out.print("\nAlteracao cancelada.");
         }//end switch     
      }//end else
   }//end alterarGenero
}//end class
