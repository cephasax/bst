/**
* @author Victor Vilar / Cephas Barreto
*/

public class BST {

	//Raiz da árvore
	private Nodo root;

	//Construtor da BST	
	public BST() {
		this.root = null;
	}
	
	//metodo de busca
	public Nodo busca(int valor){
		  Nodo nodoatual = this.root;
		  while(nodoatual != null){
			  if(valor == nodoatual.getvalor()){
				  return nodoatual;
			  }
			  else if(valor < nodoatual.getvalor()){
				  nodoatual = nodoatual.getfilho_e();
			  }
			  else{
				  nodoatual = nodoatual.getfilho_d();
			  }
		  }
		  return null;
	}

	
	// Método de busca indexada
	public Nodo busca_index(int key){
	Nodo nodoatual = this.root;
	int filhos = nodoatual.getqtdfilhos_e();
	int key_base = key;
	
	while(key_base != filhos || nodoatual != null){
		if(key_base < filhos){
			nodoatual = nodoatual.getfilho_e();
			filhos = nodoatual.getqtdfilhos_e();
		}
		else if(key_base > filhos){
			nodoatual = nodoatual.getfilho_d();
			key_base = key_base - filhos;
			filhos = nodoatual.getqtdfilhos_e();
		}
		else if(key_base == filhos){
			return nodoatual;
		} 
	}
	return null;
}
	
	//método insert
	public void insert(int valor) {
	    Nodo newNode = new Nodo(valor);
	    if (root == null) {
	      root = new Nodo(newNode.getvalor());
	    } else {
	      insert(root, newNode);
	    }
	}

	private void insert(Nodo currentNode, Nodo newNode) {
	    if (newNode.getvalor() < currentNode.getvalor()) {
	      currentNode.setqtd_filhos_e(currentNode.getqtdfilhos_e() + 1);
	      if (currentNode.getfilho_e() == null){
	        newNode.setpai(currentNode);
	        currentNode.setfilho_e(newNode);
	      } else 
	        insert(currentNode.getfilho_e(), newNode);
	    }
	    if (newNode.getvalor() > currentNode.getvalor()) {
	      if (currentNode.getfilho_d() == null){
	        newNode.setpai(currentNode);
	        currentNode.setfilho_d(newNode);
	      }
	      else
	        insert(currentNode.getfilho_d(), newNode);
	    }
	}
	
	//metodo troca
	private void troca_nodo( Nodo a, Nodo b){
        int aux = a.getvalor();
        a.setvalor(b.getvalor());
        b.setvalor(aux);
	}
	
	//metodo remove
	public void remove(int index){
	    Nodo nodoatual = this.root;
	    Nodo pai;
	    Nodo aux;
	    int filhos = nodoatual.getqtdfilhos_e();
	    int index_base = index;

	    while(index_base != filhos || nodoatual != null){
	        if(index_base < filhos){
	            nodoatual.downqtd_filhos_e();
	            nodoatual = nodoatual.getfilho_e();
	            filhos = nodoatual.getqtdfilhos_e();
	        }
	        else if(index_base > filhos){
	            nodoatual = nodoatual.getfilho_d();
	            index_base = index_base - filhos;
	            filhos = nodoatual.getqtdfilhos_e();
	        }
	        else { //(index_base == filhos)
	            
	            if(nodoatual.getfilho_e() == null && nodoatual.getfilho_d() == null){
	                pai = nodoatual.getpai();
	                if(pai.getfilho_e() == nodoatual){
	                    pai.setfilho_e(null);
	                }
	                else {
	                    pai.setfilho_d(null);
	                }
	                return;
	            }
	            else if (nodoatual.getfilho_e() != null && nodoatual.getfilho_d() != null) { //There are LChild and RChild
	            // if(nodoatual.getfilho_e() != null && nodoatual.getfilho_d() != null) { //There are LChild and RChild
                    aux = nodoatual.getfilho_e();
                    while(aux.getfilho_d() != null){
                        aux = aux.getfilho_d();
                    }
                    pai = nodoatual.getpai();
                    
                    if(pai.getfilho_e() == nodoatual){
                        troca_nodo(pai.getfilho_e(), aux);
                        nodoatual.downqtd_filhos_e();
                        aux.getpai().setfilho_d(null);
                        aux = null;
                    }
                    else{
                        troca_nodo(pai.getfilho_d(), aux);
                        nodoatual.downqtd_filhos_e();
                        aux.getpai().setfilho_d(null);
                        aux = null;
                    }
                    return;
	            }        
	            else if(nodoatual.getfilho_e() == null){
	                pai = nodoatual.getpai();
	                nodoatual = nodoatual.getfilho_d();
	                pai.setfilho_e(nodoatual);
	                nodoatual.setpai(pai);
	                return;
	            }
	            else if(nodoatual.getfilho_d() == null){
	                pai = nodoatual.getpai();
	                nodoatual = nodoatual.getfilho_e();
	                pai.setfilho_d(nodoatual);
	                nodoatual.setpai(pai);
	                return;
	            }

	        
	        } 
	    }
}
	
	
	public Nodo getRoot() {
		return this.root;
	}

	public void setRoot(Nodo root) {
		this.root = root;
	}
}