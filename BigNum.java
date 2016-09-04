package StringNum;

import java.util.Random;

/**
 * �����ݵĴ�ţ��Ӽ��ˣ�����ֵ��������
 * @author zuoxc
 *
 */
public class BigNum {

	//�������
	private String aNum="";

	//��ŷ���
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
	 * �Ƿ�Ϊ����
	 * @return boolean
	 */
	public boolean isNeg(){
		return negPosFlag.equals("-");
	}
	/**
	 * �Ƿ�Ϊ0
	 * @return boolean
	 */
	public boolean isZero(){
		return negPosFlag.equals("0");
	}
	/**
	 * �Ƿ�Ϊ����
	 * @return boolean
	 */
	public boolean isPos(){
		return negPosFlag.equals("+");
	}
	/**
	 * ����λ��
	 * @return
	 */
	public int length() {
		return aNum.length();
	}
	/**
	 * �����ֵ
	 * @return String
	 */
	public String getNum() {
		if(isNeg()) {
			return negPosFlag+aNum;
		}
		else return aNum;
	}
	/**
	 * ������ֵ
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
	 * ������ɶ���������
	 * @param length
	 */
	public BigNum setRandomNum(int length) {
		String numberChar = "0123456789";
		Long seed = System.currentTimeMillis();// ���ϵͳʱ�䣬��Ϊ���������������
        StringBuffer sb = new StringBuffer();// װ�����ɵ������
        Random random = new Random(seed);// �����������������
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
	 * ��С�ȽϽӿ�
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
	 * �����ʽ
	 * @param num
	 * @return �Ϸ���num
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
	 * ���
	 */
	public BigNum add(int b) {
		BigNum bNum=new BigNum(b);
		return this.add(bNum);
	}
	/**
	 * ���
	 */
	public BigNum add(String b) {
		BigNum bNum=new BigNum(b);
		return this.add(bNum);
	}
	/**
	 * ���
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
	 * ���
	 */
	public BigNum minus(int b) {
		BigNum bNum=new BigNum(b);
		return this.minus(bNum);
	}
	/**
	 * ���
	 */
	public BigNum minus(String b) {
		BigNum bNum=new BigNum(b);
		return this.minus(bNum);
	}
	/**
	 * ���
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
	 * ��˷�
	 */
	public BigNum multiple(int b){
		BigNum bNum=new BigNum(b);
		return this.multiple(bNum);
	}
	/**
	 * ��˷�
	 */
	public BigNum multiple(String b){
		BigNum bNum=new BigNum(b);
		return this.multiple(bNum);
	}
	/**
	 * ��˷�
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
	 * ����10
	 */
	public BigNum multiple10() {
		aNum+="0";
		aNum=checkForm(aNum);
		return this;
	}
	/**
	 * ����10
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
	 * �����߾���ֵ��mod
	 * @return mod(|a|,|b|)
	 */
 	public BigNum absMod(int b){
		BigNum bNum=new BigNum(b);
		return this.absMod(bNum);
	}
	public BigNum absMod(String b){
		BigNum bNum=new BigNum(b);
		return this.absMod(bNum);
	}
	public BigNum absMod(BigNum bNum) {
		bNum=bNum.abs();
		return new BigNum(countMod(aNum, bNum.abs().getNum()));
	}
	/**
	 * ȡ��
	 * @return
	 */
	public BigNum turnFlag() {
		// TODO Auto-generated method stub
		if (isNeg()) {
			negPosFlag="+";
		}
		else if(isPos()) {
			negPosFlag="-";
		}
		return this;
	}
	/**
	 * ȡ����ֵ
	 * @return
	 */
	public BigNum abs() {
		// TODO Auto-generated method stub
		if (isNeg()) {
			negPosFlag="+";
		}
		return this;
	}
	/**
	 * ���
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
	 * ���
	 * @param aNum
	 * @param bNum
	 * @return a-b
	 */
	private String minusString(String aNum, String bNum) {
		if (!aIsBiggerThanB(aNum, bNum)) {
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
	 * ��˻�
	 * @param aNum
	 * @param bNum
	 * @return a*b
	 */
	private String multipleString(String aNum, String bNum) {
		// TODO Auto-generated method stub
		if (!aIsBiggerThanB(aNum, bNum)) {
			return multipleString(bNum, aNum);
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
	 * �Ƚ�a b
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
	 * ����
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
	 * ����
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
}
