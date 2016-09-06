package StringNum;

import java.util.Random;


/**
 * 大整数的存放，加减乘除，绝对值求（余运算,最大公约数，最小公倍数）
 * @author zuoxc
 *
 */
public class BigNum {

	//存放数据
	private String aNum="";

	//存放符号
	private String negPosFlag="";

	public BigNum(String aNum) {
		// TODO Auto-generated constructor stub
		setNum(aNum);
	}
	public BigNum(int a) {
		// TODO Auto-generated constructor stub
		if (a==0) {
			negPosFlag="-";
			aNum="";
		}
		else {
			setNum(a+"");
		}
	}
	public BigNum() {
		// TODO Auto-generated constructor stub
		setNum(null);
	}
	/**
	 * 是否为负数
	 * @return boolean
	 */
	public boolean isNeg(){
		return negPosFlag.equals("-");
	}
	/**
	 * 是否为0
	 * @return boolean
	 */
	public boolean isZero(){
		return negPosFlag.equals("0");
	}
	/**
	 * 是否为正数
	 * @return boolean
	 */
	public boolean isPos(){
		return negPosFlag.equals("+");
	}
	/**
	 * 数据位数
	 * @return
	 */
	public int length() {
		return aNum.length();
	}
	/**
	 * 获得数值
	 * @return String
	 */
	public String getNum() {
		if(isNeg()) {
			return negPosFlag+aNum;
		}
		else return aNum;
	}
	/**
	 * 设置数值
	 * @param String
	 */
	public BigNum setNum(int a) {
		return setNum(a+"");
	}
	public BigNum setNum(String aNum) {
		if(aNum==null||aNum.equals("")) {
			negPosFlag="0";
			aNum="";
		}
		else {
			if (aNum.startsWith("-")) {
				negPosFlag="-";
				aNum=aNum.substring(1);
			}
			else {
				negPosFlag="+";
			}
			aNum=checkForm(aNum);
			if(aNum.equals("")) {
				negPosFlag="0";
			}
			this.aNum = aNum;
		}
		return this;
	}

	/**
	 * 随机生成定长的数字
	 * @param length
	 */
	public BigNum setRandomNum(int length) {
		String numberChar = "0123456789";
		Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子
        StringBuffer sb = new StringBuffer();// 装载生成的随机数
        Random random = new Random(seed);// 调用种子生成随机数
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return setNum(sb.toString());
	}
	@Override
	public String toString() {
		if (aNum.equals("")) {
			return "0";
		}
		else return getNum();
	}
	public boolean equals(BigNum bNum) {
		return this.getNum().equals(bNum.getNum());
	}
	/**
	 * 大小比较接口
	 * @param BigNum
	 * @return boolean
	 */

	public boolean isBiggerThan(BigNum bNum) {
		if(isZero())return bNum.isNeg();
		if (isPos()&&(bNum.isNeg()||bNum.isZero())) {
			return true;
		}
		else if (isPos()&&bNum.isPos()){
			return aIsBiggerThanB(aNum, bNum.getNum());
		}
		else if(isNeg()&&(bNum.isPos()||bNum.isZero())){
			return false;
		}
		else {
			return aIsBiggerThanB(bNum.abs().getNum(), aNum);
		}
	}
	/**
	 * 检查表达式
	 * @param num
	 * @return 合法的num
	 */
	private String checkForm(String num) {
		// TODO Auto-generated method stub
		if (num==null||"".equals(num)) {
			return num;
		}
		if (num.startsWith("0")) {
			return checkForm(num.substring(1));
		}
		else {
			for(int i=0;i<num.length();i++) {
				if (num.charAt(i)>'9'||num.charAt(i)<'0') {
					return checkForm(num.replaceAll(num.charAt(i)+"", ""));
				}
			}
		}
		return num;
	}
	
	/**
	 * 求和
	 */
	public BigNum add(int b) {
		BigNum bNum=new BigNum(b);
		return this.add(bNum);
	}
	/**
	 * 求和
	 */
	public BigNum add(String b) {
		BigNum bNum=new BigNum(b);
		return this.add(bNum);
	}
	/**
	 * 求和
	 */
	public BigNum add(BigNum bNum) {
		BigNum ans=new BigNum();
		if (isZero()) {
			ans.setNum(bNum.getNum());
			return ans;
		}
		else if (bNum.isZero()) {
			return this;
		}
		if (isPos()&&bNum.isPos()) {
			ans.setNum(addString(aNum, bNum.getNum()));
		}
		else if (isNeg()&&bNum.isNeg()) {
			ans.setNum("-"+addString(aNum, bNum.getNum().substring(1)));
		}
		else if (isPos()&&bNum.isNeg()) {
			ans.setNum(minusString(aNum, bNum.abs().getNum()));
		}
		else if (isNeg()&&bNum.isPos()) {
			ans.setNum(minusString(bNum.getNum(), aNum));
		}
		return ans;
	}
	/**
	 * 求差
	 */
	public BigNum minus(int b) {
		BigNum bNum=new BigNum(b);
		return this.minus(bNum);
	}
	/**
	 * 求差
	 */
	public BigNum minus(String b) {
		BigNum bNum=new BigNum(b);
		return this.minus(bNum);
	}
	/**
	 * 求差
	 */
	public BigNum minus(BigNum bNum) {
		BigNum ans=new BigNum();
		if (isZero()) {
			ans.setNum(bNum.turnFlag().getNum());
			return ans;
		}
		else if (bNum.isZero()) {
			return this;
		}
		if (isPos()&&bNum.isNeg()) {
			ans.setNum(addString(aNum, bNum.turnFlag().getNum()));
		}
		else if (isNeg()&&bNum.isPos()) {
			ans.setNum("-"+addString(aNum, bNum.getNum()));
		}
		else if (isPos()&&bNum.isPos()) {
			ans.setNum(minusString(aNum, bNum.getNum()));
		}
		else if (isNeg()&&bNum.isNeg()) {
			ans.setNum(minusString(bNum.turnFlag().getNum(), aNum));
		}
		return ans;
	}
	/**
	 * 求乘法
	 */
	public BigNum multiple(int b){
		BigNum bNum=new BigNum(b);
		return this.multiple(bNum);
	}
	/**
	 * 求乘法
	 */
	public BigNum multiple(String b){
		BigNum bNum=new BigNum(b);
		return this.multiple(bNum);
	}
	/**
	 * 求乘法
	 */
	public BigNum multiple(BigNum bNum) {
		if (isZero()||bNum.isZero()) {
			return new BigNum("0");
		}
		else if ((isPos()&&bNum.isPos())||(isNeg()&&isNeg())) {
			return new BigNum(multipleString(aNum,bNum.abs().getNum()));
		}
		else {
			return new BigNum("-"+multipleString(aNum,bNum.abs().getNum()));
		}
	}
	/**
	 * 乘以10
	 */
	public BigNum multiple10() {
		aNum+="0";
		aNum=checkForm(aNum);
		return this;
	}
	/**
	 * 除法
	 * @return
	 */
	public BigNum divide(int b){
		BigNum bNum=new BigNum(b);
		return this.divide(bNum);
	}
	/**
	 * 除法
	 * @return
	 */
	public BigNum divide(String b){
		BigNum bNum=new BigNum(b);
		return this.divide(bNum);
	}
	/**
	 * 除法
	 * @return
	 */
	public BigNum divide(BigNum bNum){
		if (isZero()) {
			return this;
		}
		else if(bNum.isZero()){
			return null;
		}
		else {
			if ((isPos()&&bNum.isPos())||(isNeg()&&bNum.isNeg())) {
				return new BigNum(devideString(aNum,bNum.abs().getNum()));
			}
			else return new BigNum("-"+devideString(aNum,bNum.abs().getNum()));
		}
	}

	/**
	 * 除以10
	 */
	public BigNum divide10() {
		if (length()<=1) {
			aNum="";
		}
		else {
			aNum=aNum.substring(0,aNum.length()-1);
		}
		return this;
	}
	/**
	 * 求两者绝对值的余
	 * @return mod(|a|,|b|)
	 */
 	public BigNum absMod(int b){
		BigNum bNum=new BigNum(b);
		return this.absMod(bNum);
	}
	/**
	 * 求两者绝对值的余
	 * @return mod(|a|,|b|)
	 */
	public BigNum absMod(String b){
		BigNum bNum=new BigNum(b);
		return this.absMod(bNum);
	}
	/**
	 * 求两者绝对值的余
	 * @return mod(|a|,|b|)
	 */
	public BigNum absMod(BigNum bNum) {
		bNum=bNum.abs();
		return new BigNum(countMod(aNum, bNum.abs().getNum()));
	}
		/**
	 * 求两者绝对值的最大公约数
	 * @return gcd(|a|,|b|)
	 */
 	public BigNum absDivisor(int b){
		BigNum bNum=new BigNum(b);
		return this.absDivisor(bNum);
	}
	/**
	 * 求两者绝对值的最大公约数
	 * @return gcd(|a|,|b|)
	 */
 	public BigNum absDivisor(String b){
		BigNum bNum=new BigNum(b);
		return this.absDivisor(bNum);
	}
	/**
	 * 求两者绝对值的最大公约数
	 * @return gcd(|a|,|b|)
	 */
	public BigNum absDivisor(BigNum bNum){
		BigNum aNumT=new BigNum(this.getNum());
		if (isZero()||bNum.isZero()) {
			return null;
		}
		else if (aNumT.equals(bNum)) {
			return aNumT;
		}
		else if (!aIsBiggerThanB(aNum, bNum.abs().getNum())) {
			return bNum.absDivisor(aNumT);
		}
		BigNum temp=new BigNum();
		while(!bNum.isZero())           /*通过循环求两数的余数，直到余数为0*/
		   {
		      temp=aNumT.absMod(bNum);
		      aNumT=bNum; 
		      bNum=temp;/*变量数值交换*/
		    }
		return aNumT; 
		
	}
	
	
	/**
	 * 求两者绝对值的最小公倍数
	 * @return lcm(|a|,|b|)
	 */
	public BigNum absMulitiple(int b){
		BigNum bNum=new BigNum(b);
		return this.absMulitiple(bNum);
	}
	/**
	 * 求两者绝对值的最小公倍数
	 * @return lcm(|a|,|b|)
	 */
	public BigNum absMulitiple(String b){
		BigNum bNum=new BigNum(b);
		return this.absMulitiple(bNum);
	}
	/**
	 * 求两者绝对值的最小公倍数
	 * @return lcm(|a|,|b|)
	 */
	public BigNum absMulitiple(BigNum bNum){
		BigNum aNumT=new BigNum(this.getNum());
		if (isZero()||bNum.isZero()) {
			return null;
		}
		else if (aNumT.equals(bNum)) {
			return aNumT;
		}
		BigNum temp=aNumT.absDivisor(bNum);
		return aNumT.multiple(bNum.abs()).divide(temp);
	}
	/**
	 * 取反
	 * @return
	 */
	public BigNum turnFlag() {
		// TODO Auto-generated method stub
		BigNum ans=new BigNum();
		if (isNeg()) {
			ans.setNum(aNum);
		}
		else if(isPos()) {
			ans.setNum("-"+aNum);
		}
		return ans;
	}
	/**
	 * 取绝对值
	 * @return
	 */
	public BigNum abs() {
		// TODO Auto-generated method stub
		BigNum ans=new BigNum(aNum);
		return ans;
	}
	/**
	 * 求和
	 * @param aNum
	 * @param bNum
	 * @return a+b
	 */
	private String addString(String aNum, String bNum) {

		// TODO Auto-generated method stub
		int i=aNum.length()-1;
		int j=bNum.length()-1;
		if(i>j)return addString(bNum, aNum);
		int cntFlag=0;
		StringBuilder ans=new StringBuilder();
		for(;i>=0;i--,j--) {
			int temp=aNum.charAt(i)+bNum.charAt(j)+cntFlag-'0';
			if(temp>'9') {
				temp-=10;
				cntFlag=1;
			}
			else {
				cntFlag=0;
			}
			ans.append((char)temp);
		}
		for(;j>=0;j--) {
			int temp=bNum.charAt(j)+cntFlag;
			if(temp>'9') {
				temp-=10;
				cntFlag=1;
			}
			else {
				cntFlag=0;
			}
			ans.append((char)temp);
		}
		if (cntFlag==1) {
			ans.append((char)(cntFlag+'0'));
		}
		return checkForm(ans.reverse().toString());
	}
	
	/**
	 * 求差
	 * @param aNum
	 * @param bNum
	 * @return a-b
	 */
	private String minusString(String aNum, String bNum) {
	
		if (aNum.equals(bNum)) {
				return "0";
		}
		else if (!aIsBiggerThanB(aNum, bNum)) {
			return "-"+minusString(bNum, aNum);
		}
		
		StringBuilder ans=new StringBuilder();
		int i=aNum.length()-1;
		int j=bNum.length()-1;
		int borrowFlag=0;
		for (; j>=0;i--,j--) {
			int temp=aNum.charAt(i)-borrowFlag-bNum.charAt(j)+'0';
			if (temp<'0') {
				temp+=10;
				borrowFlag=1;
			}
			else borrowFlag=0;
			ans.append((char)temp);
		}
		for(;i>=0;i--) {
			int temp=aNum.charAt(i)-borrowFlag;
			if (temp<'0') {
				temp+=10;
				borrowFlag=1;
			}
			else borrowFlag=0;
			ans.append((char)temp);
		}
		return checkForm(ans.reverse().toString());
	}
	
	/**
	 * 求乘积
	 * @param aNum
	 * @param bNum
	 * @return a*b
	 */
	private String multipleString(String aNum, String bNum) {
		// TODO Auto-generated method stub
		if (!aIsBiggerThanB(aNum, bNum)) {
			return multipleString(bNum, aNum);
		}
		else if (aNum.equals("1")) {
			return bNum;
		}
		else if (bNum.equals("1")) {
			return aNum;
		}
		PreCaculate(bNum);
		BigNum ans=new BigNum();
		for(int i=0;i<aNum.length();i++) {
			int num=aNum.charAt(i)-'0';
			ans=ans.add(this.shishang[num]).multiple10();
		}
		ans=ans.divide10();
		return ans.getNum();
	}
	/**
	 * 比较a b
	 * @param a
	 * @param b
	 * @return a>b?
	 */
	private boolean aIsBiggerThanB(String a,String b) {
		int alen=a.length();
		int blen=b.length();
		if(alen<blen)return false;
		else if (alen>blen) {
			return true;
		}
		else {
			return a.compareTo(b)>0;
		}
	}
	/**
	 * 求余
	 * @param aNum
	 * @param bNum
	 * @return mod(aNum,bNum)
	 * @author zxc
	 */
	private  String countMod(String aNum, String bNum) {
		// TODO Auto-generated method stub
		//PreCaculate(bNum);
		while (aNum.length()>bNum.length()||(aNum.length()==bNum.length()&&aNum.compareTo(bNum)>=0)) {
			aNum=countModtrue(aNum, bNum);
			
		}
		return aNum;
	}

	private  String countModtrue(String aNum, String bNum) {
		// TODO Auto-generated method stub
		if (bNum==null||"".equals(bNum)||aNum==null||"".equals(aNum)||"0".equals(bNum)) {
			return null;
		}
		if (aNum.length()<bNum.length()) {
			return aNum;
		}
		else if (aNum.length()==bNum.length()&&aNum.compareTo(bNum)<0) {
			return aNum;
		}
		int len=bNum.length();
		String aShort=aNum.substring(0,len);
		if(aShort.compareTo(bNum)<0)
			{
				len+=1;
				aShort=aNum.substring(0,len);
			}
		aShort=checkForm(minusCountMod(aShort,bNum));
		aShort+=aNum.substring(len);
		aNum=null;
		return aShort;
	}

	private String minusCountMod(String aNum, String bNum) {
		// TODO Auto-generated method stub
		int i=aNum.length()-1;
		int j=bNum.length()-1;
		StringBuilder ans=new StringBuilder(); 
		int cntFlag=0;
		for(;j>=0;j--,i--)
		{
			int temp=aNum.charAt(i)-bNum.charAt(j)-cntFlag;
			if (temp<0) {
				temp+=10;
				cntFlag=1;
			}
			else cntFlag=0;
			char a=(char)(temp+'0');
			ans.append(a);
		}
		if(i==0) {
			int temp=(aNum.charAt(i)-cntFlag);
			if(temp!=0) {
				char a=(char)(temp);
				ans.append(a);
			}
		}
		aNum=null;
		String ansNum=checkForm(ans.reverse().toString());
		ans=null;
		if (ansNum.length()<bNum.length()) {
			return ansNum;
		}
		else if (ansNum.length()==bNum.length()&&ansNum.compareTo(bNum)<0) {
			return ansNum;
		}
		
		return minusCountMod(ansNum, bNum);
	}

	/**
	 * 试商
	 * @param bNum
	 */
	private void PreCaculate(String bNum) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++)
		{
			if (i==0) {
				shishang[0]=new String("0");
			}
			else {
				shishang[i]=new String(addString(bNum,shishang[i-1]));
			}
		}
	}
	private String[] shishang=new String[10];
	
	private String devideString(String aNum, String bNum) {
		// TODO Auto-generated method stub
		if (aNum.equals(bNum)) {
			return "1";
		}
		else if (!aIsBiggerThanB(aNum, bNum)) {
			return "0";
		}
		else if (bNum.equals("1")) {
			return aNum;
		}
		PreCaculate(bNum);
		int blen=bNum.length();
		StringBuilder ans=new StringBuilder();
		String aNumLeft=aNum.substring(0,blen-1);
		String aNumRight=aNum.substring(blen-1);
		for(int i=0;i<aNumRight.length();i++){
			aNumLeft=aNumLeft+aNumRight.charAt(i);
			devideResult result=devideByShiShang(aNumLeft,bNum);
			aNumLeft=result.numLeave;
			ans.append(result.resultShang);
		}
		return checkForm(ans.toString());
	}
	
	private devideResult devideByShiShang(String aNum, String bNum) {
		// TODO Auto-generated method stub
		int alen=aNum.length();
		int blen=bNum.length();
		int i;
		if(alen>blen){
			for (i=9; i >=0; i--) {
				if (aIsBiggerThanB(aNum, shishang[i])||aNum.equals(shishang[i])) {
					break;
				}
			}
		}
		else if (alen<blen) {
			i=0;
		}
		else {
			for(i=0;i<10;i++){
				if (aNum.equals(shishang[i])) {
					break;
				}
				else if (!aIsBiggerThanB(aNum, shishang[i])) {
					i--;
					break;
				}
			}
		}
		devideResult result=new devideResult();
		result.numLeave=checkForm(minusString(aNum, shishang[i]));
		result.resultShang=(char)(i+'0');
		return result;
	}

	public class devideResult{
		public String numLeave="";
		public char resultShang='0';
	}
}
