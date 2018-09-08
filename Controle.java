import java.io.*;

/** Estrutura dos registros:
 *| lapide | tamanho da entidade | id + entidade |
**/

public class Controle{

   private RandomAccessFile raf;
   private RandomAccessFile raf2;

   /**
    * Metodo que busca a posicao do filme a ser excluido e a ser pesquisado
    * @param int id, id a ser buscada
   **/
   public long busca(int id){
      long pos = -1;
      long posBusca;
      boolean found = false;
      int id2 = -1;
   
      try{
         raf.seek(4);
      
         while(!found){
            pos = raf.getFilePointer();
            char lap = raf.readChar();
            int tam = raf.readInt();
            id2 = raf.readInt();//id presente no arquivo
            if(id2 == id && lap == ' '){//se a id buscada for igual a do arquivo e a lapide estiver desmarcada, achou o item buscado
               found = true;
            } else {
               raf.skipBytes(tam - 4);
            }
         }
      } catch (EOFException e) {
         pos = -1;
      } catch (IOException e2) {
         e2.printStackTrace();
      }
   
      if(found){
         posBusca = pos;
      }
      else posBusca = -1;
   
      return posBusca;
   }//end busca
   
   /**
    * Metodo de mostrar o filme buscado
    * @param int id
   **/
   public void mostrarFilme(int id){
      try{
         long pos = this.busca(id);
         if(pos == -1){
            System.out.print("\nFilme nao encontrado.\n");
         }else{
            raf.seek(pos);
            raf.readChar();
            raf.readInt();
            System.out.print("\nID: "+raf.readInt());
            System.out.print("\nTitulo: "+raf.readUTF());
            System.out.print("\nTitulo Original: "+raf.readUTF());
            System.out.print("\nPais de Origem: "+raf.readUTF());
            System.out.print("\nAno de lancamento: "+raf.readShort());
            System.out.print("\nDuracao em minutos: "+raf.readShort());
            System.out.print("\nDiretor: "+raf.readUTF());
            System.out.print("\nSinopse: "+raf.readUTF());
            System.out.print("\nGenero: "+raf.readUTF()+"\n");
         }
      } catch (IOException e2) {
         e2.printStackTrace();
      }
   }//end mostrar
   
   /**
    * Metodo de mostrar o genero buscado
    * @param int id
   **/
   public void mostrarGenero(int id){
      try{
         long pos = this.busca(id);
         if(pos == -1){
            System.out.print("\nGenero nao encontrado.\n");
         }else{
            raf.seek(pos);
            raf.readChar();
            raf.readInt();
            System.out.print("\nID do Genero: "+raf.readInt());
            System.out.print("\nNome do Genero: "+raf.readUTF()+"\n");
         }
      } catch (IOException e2) {
         e2.printStackTrace();
      }
   
   }//end mostrar

   /**
    * Metodo que busca a posicao e coloca a lapide para a exclusao
    * @param long pos, posicao a ser buscada
   **/
   public void excluir(long pos){
      try{
         raf.seek(pos);
         raf.writeChar('*');
      } catch (IOException e){
         e.printStackTrace();
      }
   }//end excluir
   
   /**
    * Metodo de inclusao da lapide, do tamanho da entidade e da propria entidade
    * @param char lap, array de byte
   **/
   public void incluir(char lap, byte[] entidade){
      try {
         raf.writeChar(lap);
         raf.writeInt(entidade.length);
         raf.write(entidade);
      } catch(IOException e) {
         e.printStackTrace();
      }
   }//end incluir

   /**
    * Metodo que cria nova ID
   **/
   public int novoID(){
      int id = -1;
      try {
         raf.seek(0);
         id = raf.readInt();
         raf.seek(0);
         id++;
         raf.writeInt(id);
         raf.seek(0);
      } catch (IOException e) {
         e.printStackTrace();
      }
   
      return id;
   }//end novoID
   
   /**
    * Metodo que vai para o fim do arquivo apos inclusao
   **/
   public void vaiPFim(){
      try {
         this.raf.seek(raf.length());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }//end vaiPFim

   public Controle(String arquivo){
      try{
         raf = new RandomAccessFile(arquivo, "rw");
         raf.writeInt(0); //Escreve o primeiro ID no cabecalho
      } catch (IOException e){
         e.printStackTrace();
      }
   }//end Controle

}//end class
