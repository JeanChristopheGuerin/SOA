package callService;


/**
 * Class who called the webService used to Convert money.
 * This class can eventually use all of the method in this WebService
 * */
public class CallMoneyChangeService {
	CurrencyConvertor c;
	CurrencyConvertorSoap cs;
	
	public CallMoneyChangeService(){
		CurrencyConvertor c = new CurrencyConvertor();
		CurrencyConvertorSoap cs = c.getCurrencyConvertorSoap();
	}
	/**
	 * Method which convert euro in dollar
	 * @return double : the result send by the webService
	 * @param euro: the value to be converted
	 * 
	 * */
	public double euroTDollar(float euro){
		return euro*cs.conversionRate(Currency.USD, Currency.EUR);
	}
	/**
	 * Method which convert euro in dollar
	 * @return double : the result send by the webService
	 * @param dollar: the value to be converted
	 * 
	 * */
	public double dollarToEuro(float dollar){
		return dollar*cs.conversionRate(Currency.EUR,Currency.USD);
	}
	
	

}