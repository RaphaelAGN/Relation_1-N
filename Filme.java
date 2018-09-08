import java.io.*;

//Classe construtora dos filmes
public class Filme{
   private int id;
   private String titulo;
   private String titulo_ori;
   private String pais;
   private short ano;
   private short duracao_min;
   private String diretor;
   private String sinopse;
   private int idGenero;

   public byte[] getByteArray() throws IOException {
      ByteArrayOutputStream filmes = new ByteArrayOutputStream();
      DataOutputStream saida = new DataOutputStream(filmes);
   
      saida.writeInt(this.id);
      saida.writeUTF(this.titulo);
      saida.writeUTF(this.titulo_ori);
      saida.writeUTF(this.pais);
      saida.writeShort(this.ano);
      saida.writeShort(this.duracao_min);
      saida.writeUTF(this.diretor);
      saida.writeUTF(this.sinopse);
      saida.writeInt(this.idGenero);
   
      return filmes.toByteArray();
   }//end getByteArray

   public void setByteArray(byte[] bytes) throws IOException {
      ByteArrayInputStream filmes = new ByteArrayInputStream(bytes);
      DataInputStream entrada = new DataInputStream(filmes);
   
      setIDFilme(entrada.readInt());
      setTitulo(entrada.readUTF());
      setTituloOri(entrada.readUTF());
      setPais(entrada.readUTF());
      setAno(entrada.readShort());
      setDuracao(entrada.readShort());
      setDiretor(entrada.readUTF());
      setSinopse(entrada.readUTF());
      setIDGenero(entrada.readInt());
   }//end setByteArray

	//Construtores
   public Filme(){}
   public Filme(String titulo){
      setTitulo(titulo);
   }

	//Setters
   public void setIDFilme(int id) { 
      this.id = id; 
   }
   public void setTitulo(String titulo) { 
      this.titulo = titulo; 
   }
   
   public void setTituloOri(String titulo_ori) { 
      this.titulo_ori = titulo_ori; 
   }
   
   public void setPais(String pais) { 
      this.pais = pais; 
   }
   
   public void setAno(short ano) { 
      this.ano = ano; 
   }
   
   public void setDuracao(short duracao) { 
      this.duracao_min = duracao; 
   }
   
   public void setDiretor(String diretor) { 
      this.diretor = diretor; 
   }
   
   public void setSinopse(String sinopse) { 
      this.sinopse = sinopse; 
   }
   
   public void setIDGenero(int idGenero) { 
      this.idGenero = idGenero; 
   }

	// Getters
   public int getId() { 
      return this.id; 
   }
   
   public String getTitulo() { 
      return this.titulo; 
   }
   
   public String getTituloOri() { 
      return this.titulo_ori; 
   }
   public String getPais() { 
      return this.pais; 
   }
   
   public short getAno() { 
      return this.ano; 
   }
   
   public short getDuracao() { 
      return this.duracao_min; 
   }
   
   public String getDiretor() { 
      return this.diretor; 
   }
   
   public String getSinopse() { 
      return this.sinopse; 
   }
   
   public int getIDGenero() { 
      return this.idGenero; 
   }

}//end class Filme
