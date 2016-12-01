package callService;



public class CallMoneyChangeService {
	CurrencyConvertor c;
	CurrencyConvertorSoap cs;
	
	public CallMoneyChangeService(){
		CurrencyConvertor c = new CurrencyConvertor();
		CurrencyConvertorSoap cs = c.getCurrencyConvertorSoap();
	}
	public double euroTDollar(float euro){
		return cs.conversionRate(Currency.USD, Currency.EUR);
	}
	public double dollarToEuro(float dollar){
		return cs.conversionRate(Currency.EUR,Currency.USD);
	}
	
	

}