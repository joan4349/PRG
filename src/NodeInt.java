
public class NodeInt{
	
	public int data;//lo declaro public porque puedo
	
	public NodeInt next;//variable referencia para apuntar objetos de tipo nodo int
	//clase del nodo de enteros
	
	public NodeInt(int d){//esta por ser el
		data = d;
	    next = null;
	}
	
	public NodeInt(int d, NodeInt n) {
		data = d;
		next = n;
	}
}
