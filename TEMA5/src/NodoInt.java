
public class NodoInt{
	
	public int data;//lo declaro public porque puedo
	
	public NodoInt next;//variable referencia para apuntar objetos de tipo nodo int
	//clase del nodo de enteros
	
	public NodoInt(int d){//esta por ser el
		data = d;
	    next = null;
	}
	
	public NodoInt(int d, NodoInt n) {
		data = d;
		next = n;
	}
}
