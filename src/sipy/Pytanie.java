package sipy;

public class Pytanie
{
	private String pytanie;
	private String a;
	private String b;
	private String c;
	private String poprawne;

	public Pytanie() {
	}

	public Pytanie(String pytanie, String a,String  b, String c,String poprawne) {
		this.pytanie = pytanie;
		this.a = a;
		this.b = b;
		this.c = c;
		this.poprawne = poprawne;
	}

	public String getPytanie()
	{
		return pytanie;
	}
	public void setPytanie(String pytanie)
	{
		this.pytanie = pytanie;
	}
	public String getA()
	{
		return a;
	}
	public void setA(String a)
	{
		this.a = a;
	}
	public String getB()
	{
		return b;
	}
	public void setB(String b)
	{
		this.b = b;
	}
	public String getC()
	{
		return c;
	}
	public void setC(String c)
	{
		this.c = c;
	}
	public String getPoprawne()
	{
		return poprawne;
	}
	public void setPoprawne(String poprawne)
	{
		this.poprawne = poprawne;
	}
}
