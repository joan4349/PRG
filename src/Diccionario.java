import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Diccionario {

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
            p = p.substring(0,1).toUpperCase() + p.substring(1);
            s = s.substring(0,1).toUpperCase() + s.substring(1);
            first = new NodeString(p,s,first);
            size++;
        }
        else if(!ant.word.equals(p)){
                p = p.substring(0,1).toUpperCase() + p.substring(1);
                s = s.substring(0,1).toUpperCase() + s.substring(1);
                ant.next = new NodeString(p,s,aux);
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
        		res = "The word " + p + " doesn`t exist in the dictionary";
        	}else{
        	res = "Palabra: " + p + "\t Traducci�n: " + aux.traduction;
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


public Diccionario union(Diccionario other) {
        Diccionario result = new Diccionario();
        NodeString aux1 = this.first; // nodo a revisar del conjunto this
        NodeString aux2 = other.first; // nodo a revisar de otro
        NodeString lastResult = null; // último nodo de result, inicialmente null
        while (aux1 != null && aux2 != null) {
            
            if(aux1.word.equalsIgnoreCase(aux2.word)){
                if(result.first == null){
                    
                    result.first = new NodeString(aux1.word, aux1.traduction ,result.first);
                    
                    lastResult = result.first;
                }
                else{
                    lastResult.next = new NodeString(aux1.word, aux1.traduction, null);
                    lastResult = lastResult.next;
                }
                result.size++;
                aux1 = aux1.next;
                aux2 = aux2.next;
            }
            else{   
                if(aux1.word.compareTo(aux2.word) < 0) {
                    if(result.first == null){
                    result.first = new NodeString(aux1.word, aux1.traduction, result.first);
                    lastResult = result.first;
                    }
                    else{
                    lastResult.next = new NodeString(aux1.word, aux1.traduction, null);
                    lastResult = lastResult.next;
                }
                aux1 = aux1.next;
                }
                else {
                    if(result.first == null){
                    result.first = new NodeString(aux2.word, aux2.traduction, result.first);
                    lastResult = result.first;
                    }
                    else{
                    lastResult.next = new NodeString(aux2.word, aux2.traduction, null);
                    lastResult = lastResult.next;
                }
                aux2 = aux2.next;
            }
                result.size++;
            }
        }
        
        if(aux1 == null){
            if(result.first == null){
                result.first = new NodeString(aux2.word, aux2.traduction, result.first);
                lastResult = result.first;
                result.size++;
                aux2 = aux2.next;
            }
            while(aux2!=null){
                
                lastResult.next = new NodeString(aux2.word, aux2.traduction,null);
                aux2 = aux2.next;
                lastResult = lastResult.next;
                result.size++;
            }
            
        }
        else{
            if(result.first == null){
                result.first = new NodeString(aux1.word, aux1.traduction,result.first);
                lastResult = result.first;
                result.size++;
                aux1 = aux1.next;
            }
            while(aux1!=null){
                lastResult.next = new NodeString(aux1.word,aux2.traduction,null);
                aux1 = aux1.next;
                lastResult = lastResult.next;
                result.size++;
            }
        }
        return result;
                
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
        String res= "Palabra--------Traducci�n" + "\n";
        while(i <= size){
            res += i + "." + aux.word + "   " + aux.traduction;
            res += "\n";
            i++;
            
        }
        return res;
	}
}
