package MODEL;


import java.io.Serializable;

public class Arc implements Serializable
	{	String lab; int u, v; float cost;
		public Arc(String lab, int u, int v, float cost)
		{	this.lab = lab;
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
                public String getlab(){
                    return lab;
                }
                public int getU(){
                    return u;
                }
                public int getV(){
                    return v;
                }
                public float getCost(){
                    return cost;
                }
	}