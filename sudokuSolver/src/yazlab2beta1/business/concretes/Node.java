package yazlab2beta1.business.concretes;

import yazlab2beta1.entities.concretes.Column;

public class Node {

	public Node l;
	public Node r;
	public Node u;
	public Node d;
	public Column c;
	public int n;

	public Node(Column c, int n) {	
	    this.l = this;
	    this.r = this;

	    this.u = this;
	    this.d = this;

	    this.c = c;
	    this.n = n;

	    if (c != null) {
	    	c.add(this);
	    }
	}

	public void remove() {
	    Node n = this;

	    do {
			n.c.cover();
			n = n.r;
	    }

	    while (n != this);
	}
	
	public void add(Node n) {
	    n.l = this.l;
	    n.r = this;

	    this.l.r = n;
	    this.l = n;
	}
	
}
