import java.io.*;

//Classe construtora do arquivo de indices de genero
public class Index {
	private int id;

	public Index() {
	}//end Index()

	/*
	 * Construtor da classe
	 * @param inteiro id
	 * @return Instancia de index criada com parametros selecionados
	 * */
	public Index(int id) {
		this.id = id;
	}//end Genero()


	public void setID(int id){
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	/**
	 * Retorna um vetor de bytes(registro) do index corrente
	**/	
	public byte[] getByteArray() throws IOException {
		ByteArrayOutputStream index = new ByteArrayOutputStream();
		DataOutputStream saida = new DataOutputStream(index);
		
		saida.writeInt(this.id);

		return index.toByteArray();
	}//end getByteArray()

	/**
	 * Recebe um vetor de bytes com o index e seta no index corrente
	 * @param vetor de bytes com informacoes de um index do arquivo
	**/
	public void setByteArray(byte[] bytes) throws IOException {
		ByteArrayInputStream index = new ByteArrayInputStream(bytes);
		DataInputStream entrada = new DataInputStream(index);

		setID(entrada.readInt());
		
	}//end setByteArray()
}//end Genero