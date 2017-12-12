package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;
	
	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) {
		super();
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;

	}

	public Retirement() {

	}
	
	public double AmountToSave() {
		double dMonthsToWork = iYearsToWork * 12;
		dAnnualReturnWorking = (dAnnualReturnWorking / 100) / 12;
		double pmt = FinanceLib.pmt(dAnnualReturnWorking, dMonthsToWork, 0, this.TotalAmountSaved(), false);
		pmt = Math.round(pmt * 100.0) / 100.0;
		return pmt;
	}
	
	public double TotalAmountSaved()
	{
		double difference  = dRequiredIncome-dMonthlySSI;
		double top = difference*(1-Math.pow(1+(dAnnualReturnRetired/1200),-iYearsRetired*12));
		double bottom = dAnnualReturnRetired/1200;
		bottom = Math.round(top*100.00/bottom)/100.00;
		return bottom;
	}
		
		public int getiYearsToWork() {
			return iYearsToWork;
		}

		public void setiYearsToWork(int iYearsToWork) {
			this.iYearsToWork = iYearsToWork;
		}

		public double getdAnnualReturnWorking() {
			return dAnnualReturnWorking;
		}

		public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
			this.dAnnualReturnWorking = dAnnualReturnWorking;
		}

		public int getiYearsRetired() {
			return iYearsRetired;
		}

		public void setiYearsRetired(int iYearsRetired) {
			this.iYearsRetired = iYearsRetired;
		}

		public double getdAnnualReturnRetired() {
			return dAnnualReturnRetired;
		}

		public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
			this.dAnnualReturnRetired = dAnnualReturnRetired;
		}

		public double getdRequiredIncome() {
			return dRequiredIncome;
		}

		public void setdRequiredIncome(double dRequiredIncome) {
			this.dRequiredIncome = dRequiredIncome;
		}

		public double getdMonthlySSI() {
			return dMonthlySSI;
		}

		public void setdMonthlySSI(double dMonthlySSI) {
			this.dMonthlySSI = dMonthlySSI;
	}
}