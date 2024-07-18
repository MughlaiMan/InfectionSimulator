package main;

public class timeStep {
	
	private int N;
	private int T;
	private double iRate;
	private double rRate;
	
	public void setNumIndividuals(int numIndividuals) {
		this.N = numIndividuals;
	}
	
	public void setNumTimeSteps(int numTimeSteps) {
		this.T = numTimeSteps;
	}
	
	public void setInfectionRate(double infectionRate) {
		this.iRate = infectionRate;
	}
	
	public void setRecoveryRate(double recoveryRate) {
		this.rRate = recoveryRate;
	}
	
	
	
	
	
	
	
	public void doTimeStep(String [][] individuals2DArray, int nTimeSteps) {
		
		for (int i=0; i<nTimeSteps; i++) {
			
			
			
		}
		
	}
	
	
}
