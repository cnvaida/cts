package delphi.netstudent.observer.pattern;

public interface Subiect {
	public void atribuireObservator(Observator o);
	public void inlaturareObservator(Observator o);
	public void notificare();
}
