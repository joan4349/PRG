import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Diccionario {
	
	private static final int CAPACITAT = 50;//la paraula encarregada de la capacitat
	private NodeString first;
	private int size;
	
	/**create new trductor if it does not exists**/
	public Diccionario() {
		
	}
	
	/**crea diccionario a parte de un archivo de texto 
	 * @param String dicreccion archivo
	 * **/
	public Diccionario(String fil) {

		String word = "", traduction ="", description = "";
		try {
			Scanner sc = new Scanner(new File(fil));
			while(sc.hasNext()) {
				word = sc.next();
				traduction = sc.next();
				description = sc.nextLine();
				insert(word, traduction, description);
				System.out.println(word+ "+"+traduction);
			}
		}catch(IOException o){
			System.err.println("No existe el archivo");
		}
	}
	
	/**
	 * Insert new word
	 * @param String: param, String: s, String: p
	 * **/
	public void insert (String p, String s, String desc) {
				
		NodeString aux = first;
        NodeString ant = null;
        String descrip = "N";
        
        if(desc.length() == 0) {
        	descrip = "Without description.";
        }else {
        	descrip = desc;
        }
        
        while(aux != null && (aux.word.compareToIgnoreCase(p))<=0){
            ant = aux;
            aux = aux.next;
        }
        
        if(aux == first){
            first = new NodeString(p,s, descrip, first);
            size++;
        }
        else{
            if(ant.word.equals(p)){}
            else{
                ant.next = new NodeString(p,s,desc,aux);
                size++;
            }
        }
    }
	/**
	 * Quit the word
	 * **/
	public void remove(String p) {
		 NodeString aux = first;
	    	NodeString ant = null;
	    	if(size == 0){throw new NoSuchElementException();}
	    	while(aux != null && !aux.word.equalsIgnoreCase(p)){
	        	ant = aux;
	        	aux = aux.next;
	    	}
	    	if(aux == first){
	        	first = first.next;
	    	}
	    	else{
	        	ant.next = aux.next;
	    	}
	}

	/**Search word
	 * @param String p
	 * @return String palabra
	 * **/
	public String search(String p) {
		NodeString aux = this.first;
    	while(aux != null && !aux.word.equalsIgnoreCase(p)){
        	aux = aux.next;
    	}
    	String res = "";
    	
    	if(aux == null){
        	res = "The word " + p + " doesn`t exist int the dictionary";
        	}
    	else{
        	res = "Word: " + p + ". > Traduction: " + aux.traduction + ". > Definition: " + aux.description + ".";
    	}
    	
    	return res;
	}

	
	/**
	 * ExistePalabra
	 * @return boolean existe
	 **/
	public boolean wordExists(String p) {
	NodeString aux = first; // auxiliar
    	
    	while(aux != null && !aux.word.equalsIgnoreCase(p.trim())){
        	aux = aux.next;
    	}
    	return aux.word.equalsIgnoreCase(p.trim());
		
		//hecho
	}
	
	/**Size
	 * @return int size
	 * **/
	public int size() {
		return size;
	}
	
	/**
	 * @return String palabra + traduccion
	 * **/
	
	/**
 	* Muestreo del diccionario
 	*/
	public String toString(){
    	NodeString aux = first;
    	int i = 1;
    	String res= "Palabra--------Traducción";
    	while(i <= size){
        	res += i + "." + aux.word + "   " + aux.traduction;
        	i++;
        	
    	}
    	return res;
	}
}
