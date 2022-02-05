package yazlab2beta1.entities.concretes;

import yazlab2beta1.business.concretes.Node;

public class Column extends Node {

	public int s;

	public Column(Column c, int n) {
	    super(null, n);

	    if (c != null) {
	    	c.add(this);
	    }
	}

	public void cover() {

	    r.l = l;
	    l.r = r;

	    for (Node i = d; i != this; i = i.d) {
    	
	    	for (Node j = i.r; j != i; j = j.r) {

			    j.u.d = j.d;
			    j.d.u = j.u;

			    j.c.s--;
			}
	    	
	    }
	
	}

	public void uncover() {

	    for (Node i = u; i != this; i = i.u) {

			for (Node j = i.l; j != i; j = j.l) {

			    j.u.d = j;
			    j.d.u = j;

			    j.c.s++;
			}
	    }

	    r.l = this;
	    l.r = this;
	}

	public void add(Column c) {
	    c.l = this.l;
	    c.r = this;

	    this.l.r = c;
	    this.l = c;
	}

	public void add(Node n) {
	    n.u = this.u;
	    n.d = this;

	    this.u.d = n;
	    this.u = n;

	    s++;
	}
    
}
