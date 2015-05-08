/**
* @Title: Regexp.java   
* @Copyright 2010 -2013 CreativeWise
* @Package com.util   
* @Description:    
* @author guangchao    
* @date 2014-2-17 下午12:49:58   
* @version V1.0 
*/
package com.util;

import java.util.*;

import org.apache.oro.text.regex.*;   

/**
 * 类简介: 使用正则表达式验证数据或提取数据,类中的方法全为静态的<br/>
 * 主要方法:1. isHardRegexpValidate(String source, String regexp)
 *             区分大小写敏感的正规表达式批配
 *          2. isSoftRegexpValidate(String source, String regexp)
 *             不区分大小写的正规表达式批配
 *          3. getHardRegexpMatchResult(String source, String regexp)
 *             返回许要的批配结果集(大小写敏感的正规表达式批配)
 *          4. getSoftRegexpMatchResult(String source, String regexp)
 *             返回许要的批配结果集(不区分大小写的正规表达式批配)
 *          5  getHardRegexpArray(String source, String regexp)
 *             返回许要的批配结果集(大小写敏感的正规表达式批配)
 *          6. getSoftRegexpMatchResult(String source, String regexp)
 *             返回许要的批配结果集(不区分大小写的正规表达式批配)
 *          7.  getBetweenSeparatorStr(final String originStr,final char leftSeparator,final char rightSeparator)
 *             得到指定分隔符中间的字符串的集合
 * 正规表达式目前共 25种
 *          1.匹配图象
               icon_regexp;
            2 匹配email地址
             email_regexp;
            3 匹配匹配并提取url
             url_regexp;
            4 匹配并提取http
               http_regexp;
            5.匹配日期
                date_regexp;
            6 匹配电话
               phone_regexp;
            7 匹配身份证
              ID_card_regexp;
            8 匹配邮编代码
              ZIP_regexp
            9. 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号:	数学减号- 右尖括号> 左尖括号<  反斜杠\ 即空格,制表符,回车符等
                non_special_char_regexp;
            10 匹配非负整数（正整数 + 0)
              non_negative_integers_regexp;
            11  匹配不包括零的非负整数（正整数 > 0)
               non_zero_negative_integers_regexp;
            12 匹配正整数
               positive_integer_regexp;
            13  匹配非正整数（负整数 + 0）
              non_positive_integers_regexp;
            14 匹配负整数
               negative_integers_regexp;
            15. 匹配整数
              integer_regexp;
            16 匹配非负浮点数（正浮点数 + 0）
              non_negative_rational_numbers_regexp
            17. 匹配正浮点数
               positive_rational_numbers_regexp
            18 匹配非正浮点数（负浮点数 + 0）
                 non_positive_rational_numbers_regexp;
            19 匹配负浮点数
                 negative_rational_numbers_regexp;
            20 .匹配浮点数
                  rational_numbers_regexp;
            21. 匹配由26个英文字母组成的字符串
                   letter_regexp;
            22. 匹配由26个英文字母的大写组成的字符串
                 upward_letter_regexp;
            23 匹配由26个英文字母的小写组成的字符串
                 lower_letter_regexp
            24 匹配由数字和26个英文字母组成的字符串
                  letter_number_regexp;
            25  匹配由数字、26个英文字母或者下划线组成的字符串
                   letter_number_underline_regexp;
            26  匹配存在中文的字符串
                   chinese_regexp;

 *
 *
 *
 */
public final class Regexp
{

    /**  保放有四组对应分隔符 */
    static final  Set<String> SEPARATOR_SET=new TreeSet<String>();
    {
               SEPARATOR_SET.add("(");
               SEPARATOR_SET.add(")");
               SEPARATOR_SET.add("[");
               SEPARATOR_SET.add("]");
               SEPARATOR_SET.add("{");
               SEPARATOR_SET.add("}");
               SEPARATOR_SET.add("<");
               SEPARATOR_SET.add(">");
    }


	/** 存放各种正规表达式(以key->value的形式) */
	 public static HashMap<String, String> regexpHash = new HashMap<String, String>();

	/** 存放各种正规表达式(以key->value的形式) */
	public static  List<Object> matchingResultList = new ArrayList<Object>();

   private       Regexp()
    {

    }
    /**
     * 返回 Regexp 实例
     * @return
     */
    public static Regexp getInstance()
    {
        return new Regexp();
    }

	/**
	 * 匹配图象 <br>
	 *
	 * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
	 *
	 * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp<br>
	 *
	 * 不匹配: c:/admins4512.gif
	 *
	 */
	public static final String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

	/**
	 * 匹配email地址 <br>
	 *
	 * 格式: XXX@XXX.XXX.XX
	 *
	 * 匹配 : foo@bar.com 或 foobar@foobar.com.au <br>
	 *
	 * 不匹配: foo@bar 或 $$$@bar.com
	 *
	 */
	public static final String email_regexp = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";

	/**
	 * 匹配匹配并提取url <br>
	 *
	 * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
	 *
	 * 匹配 : http://www.suncer.com 或news://www<br>
	 *
	 * 提取(MatchResult matchResult=matcher.getMatch()):
	 *  			matchResult.group(0)= http://www.suncer.com:8080/index.html?login=true
	 *              matchResult.group(1) = http
	 *              matchResult.group(2) = www.suncer.com
	 *              matchResult.group(3) = :8080
	 *              matchResult.group(4) = /index.html?login=true
	 *
	 * 不匹配: c:\window
	 *
	 */
	public static final String url_regexp = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * 匹配并提取http <br>
	 *
	 * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或 https://XXX
	 *
	 * 匹配 : http://www.suncer.com:8080/index.html?login=true<br>
	 *
	 * 提取(MatchResult matchResult=matcher.getMatch()):
	 *  			matchResult.group(0)= http://www.suncer.com:8080/index.html?login=true
	 *              matchResult.group(1) = http
	 *              matchResult.group(2) = www.suncer.com
	 *              matchResult.group(3) = :8080
	 *              matchResult.group(4) = /index.html?login=true
	 *
	 * 不匹配: news://www
	 *
	 */
	public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * 匹配日期 <br>
	 *
	 * 格式(首位不为0): XXXX-XX-XX 或 XXXX XX XX 或 XXXX-X-X <br>
	 *
	 * 范围:1900--2099 <br>
	 *
	 * 匹配 : 2005-04-04 <br>
	 *
	 * 不匹配: 01-01-01
	 *
	 */
	public static final String date_regexp = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";// 匹配日期
	/**
	 * 匹配日期 <br>
	 *
	 * 格式: XXXX/XX/XX  YYYY/MM/DD<br>
	 *
	 *
	 * 匹配 : 2005/04/04 <br>
	 *
	 * 不匹配: 2005/4/4
	 *
	 */
	public static final String date_regexp_yyyymmdd = "^(([0-9]{4}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)";// 匹配日期

	/**
	 * 匹配电话 <br>
	 *
	 * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或 <br>
	 * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或
	 * XXXXXXXXXXX(11位首位不为0) <br>
	 *
	 * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或
	 * 010-12345678 或 12345678912 <br>
	 *
	 * 不匹配: 1111-134355 或 0123456789
	 *
	 */
	public static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

	/**
	 * 匹配身份证 <br>
	 *
	 * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
	 * XXXXXXXXXXXXXXXXXX(18位) <br>
	 *
	 * 匹配 : 0123456789123 <br>
	 *
	 * 不匹配: 0123456
	 *
	 */
	public static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

	/**
	 * 匹配邮编代码 <br>
	 *
	 * 格式为: XXXXXX(6位) <br>
	 *
	 * 匹配 : 012345 <br>
	 *
	 * 不匹配: 0123456
	 *
	 */
	public static final String ZIP_regexp = "^[0-9]{6}$";// 匹配邮编代码


	/**
	 * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号<  反斜杠\ 即空格,制表符,回车符等 )<br>
	 *
	 * 格式为: x 或 一个一上的字符 <br>
	 *
	 * 匹配 : 012345 <br>
	 *
	 * 不匹配: 0123456
	 *
	 */
	public static final String non_special_char_regexp = "^[^'\"\\;,:-<>\\s].+$";// 匹配邮编代码


	/**
	 * 匹配非负整数（正整数 + 0)
	 */
	public static final String non_negative_integers_regexp = "^\\d+$";

	/**
	 * 匹配不包括零的非负整数（正整数 > 0)
	 */
	public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";

	/**
	 *
	 * 匹配正整数
	 *
	 */
	public static final String positive_integer_regexp = "^[0-9]*[1-9][0-9]*$";

	/**
	 *
	 * 匹配非正整数（负整数 + 0）
	 *
	 */
	public static final String non_positive_integers_regexp = "^((-\\d+)|(0+))$";

	/**
	 *
	 * 匹配负整数
	 *
	 */
	public static final String negative_integers_regexp = "^-[0-9]*[1-9][0-9]*$";

	/**
	 *
	 * 匹配整数
	 *
	 */
	public static final String integer_regexp = "^-?\\d+$";

	/**
	 *
	 * 匹配非负浮点数（正浮点数 + 0）
	 *
	 */
	public static final String non_negative_rational_numbers_regexp = "^\\d+(\\.\\d+)?$";

	/**
	 *
	 * 匹配正浮点数
	 *
	 */
	public static final String positive_rational_numbers_regexp = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

	/**
	 *
	 * 匹配非正浮点数（负浮点数 + 0）
	 *
	 */
	public static final String non_positive_rational_numbers_regexp = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

	/**
	 *
	 * 匹配负浮点数
	 *
	 */
	public static final String negative_rational_numbers_regexp = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

	/**
	 *
	 * 匹配浮点数
	 *
	 */
	public static final String rational_numbers_regexp = "^(-?\\d+)(\\.\\d+)?$";

	/**
	 *
	 * 匹配由26个英文字母组成的字符串
	 *
	 */
	public static final String letter_regexp = "^[A-Za-z]+$";

	/**
	 *
	 * 匹配由26个英文字母的大写组成的字符串
	 *
	 */
	public static final String upward_letter_regexp = "^[A-Z]+$";

	/**
	 *
	 * 匹配由26个英文字母的小写组成的字符串
	 *
	 */
	public static final String lower_letter_regexp = "^[a-z]+$";

	/**
	 *
	 * 匹配由数字和26个英文字母组成的字符串
	 *
	 */
	public static final String letter_number_regexp = "^[A-Za-z0-9]+$";

	/**
	 *
	 * 匹配由数字、26个英文字母或者下划线组成的字符串
	 *
	 */
	public static final String letter_number_underline_regexp = "^\\w+$";
	/**
	 *
	 * 匹配由数字、26个英文字母或者下划线组成的字符串
	 *
	 */
	public static final String chinese_regexp = "[\u4e00-\u9fa5]";

	/**
	 * 添加正规表达式 (以key->value的形式存储)
	 *
	 * @param regexpName
	 *            该正规表达式名称 `
	 * @param regexp
	 *            该正规表达式内容
	 */
	public void putRegexpHash(String regexpName, String regexp)
    {
		regexpHash.put(regexpName, regexp);
	}

	/**
	 * 得到正规表达式内容 (通过key名提取出value[正规表达式内容])
	 *
	 * @param regexpName
	 *            正规表达式名称
	 *
	 * @return 正规表达式内容
	 */
	public String getRegexpHash(String regexpName)
    {
		if (regexpHash.get(regexpName) != null)
        {
			return ((String) regexpHash.get(regexpName));
		}
        else
        {
			System.out.println("在regexpHash中没有此正规表达式");
			return "";
		}
	}

	/**
	 * 清除正规表达式存放单元
	 */
	public void clearRegexpHash()
    {
		regexpHash.clear();
		return;
	}

	/**
	 * 大小写敏感的正规表达式批配
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回真,否则返回假 如:  Regexp.isHardRegexpValidate("ygj@suncer.com.cn",email_regexp) 返回真
	 */
	public static boolean isHardRegexpValidate(String source, String regexp)
	{

		try
		{
			// 用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 实例大小大小写敏感的正规表达式模板
			Pattern hardPattern = compiler.compile(regexp);

			// 返回批配结果
			return matcher.contains(source, hardPattern);

		}
        catch (MalformedPatternException e)
        {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * 不区分大小写的正规表达式批配
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回真,否则返回假 Regexp.isHardRegexpValidate("ygj@suncer.com.cn",email_regexp) 返回真
	 */
	public static boolean isSoftRegexpValidate(String source, String regexp)
	{
		try
		{
			//用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 实例不区分大小写的正规表达式模板
			Pattern softPattern = compiler.compile(regexp,
					Perl5Compiler.CASE_INSENSITIVE_MASK);

			// 返回批配验证值
			return matcher.contains(source, softPattern);

		}
        catch (MalformedPatternException e)
        {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * 返回许要的批配结果集(大小写敏感的正规表达式批配)
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回许要的批配结果集,否则返回 "null"关键字 <br>
	 *         如 : MatchResult matchResult = getHardRegexpMatchResult("http://www.suncer.com:8080/index.html?login=true",Regexp.url_regexp)
	 *         得到取出的匹配值为
	 *  			matchResult.group(0)= http://www.suncer.com:8080/index.html?login=true
	 *              matchResult.group(1) = http
	 *              matchResult.group(2) = www.suncer.com
	 *              matchResult.group(3) = :8080
	 *              matchResult.group(4) = /index.html?login=true
	 *
	 */
	public static MatchResult getHardRegexpMatchResult(String source, String regexp)
	{
		try
		{
			//用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 实例大小大小写敏感的正规表达式模板
			Pattern hardPattern = compiler.compile(regexp);

			// 如果批配结果正确,返回取出的批配结果
			if (matcher.contains(source, hardPattern))
            {
				// MatchResult result=matcher.getMatch();
				return matcher.getMatch();
			}
		}
        catch (MalformedPatternException e)
        {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回许要的批配结果集(不区分大小写的正规表达式批配)
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回许要的批配结果集,否则返回 "null"关键字
     *  如 : MatchResult matchResult = getHardRegexpMatchResult("http://www.suncer.com:8080/index.html?login=true",Regexp.url_regexp)
	 *         得到取出的匹配值为
	 *  			matchResult.group(0)= http://www.suncer.com:8080/index.html?login=true
	 *              matchResult.group(1) = http
	 *              matchResult.group(2) = www.suncer.com
	 *              matchResult.group(3) = :8080
	 *              matchResult.group(4) = /index.html?login=true
	 */
	public static MatchResult getSoftRegexpMatchResult(String source, String regexp)
	{
		try
		{
			//用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 实例不区分大小写的正规表达式模板
			Pattern softPattern = compiler.compile(regexp,
					Perl5Compiler.CASE_INSENSITIVE_MASK);

			// 如果批配结果正确,返回取出的批配结果
			if (matcher.contains(source, softPattern))
            {
				// MatchResult result=matcher.getMatch();
				return matcher.getMatch();
			}

		}
        catch (MalformedPatternException e)
        {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 返回许要的批配结果集(大小写敏感的正规表达式批配)
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回许要的批配结果集,{@link #getHardRegexpMatchResult(String, String) 使用方法参见getHardRegexpMatchResult(String, String)}
	 */
	public static ArrayList<String> getHardRegexpArray(String source, String regexp)
	{
		List<String> tempList=new ArrayList<String>();

		try
		{
			//用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 实例大小大小写敏感的正规表达式模板
			Pattern hardPattern = compiler.compile(regexp);

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 如果批配结果正确,返回取出的批配结果
			if (matcher.contains(source, hardPattern))
			{
				//存放取出值的正规表达式比较批配结果对象
				MatchResult matchResult =matcher.getMatch();
				for(int i=0;i<matchResult.length() && matchResult.group(i)!=null; i++)
				{
					tempList.add(i,matchResult.group(i));
				}
			}
		}
        catch (MalformedPatternException e)
        {
			e.printStackTrace();
		}
		return (ArrayList<String>)tempList;
	}

	/**
	 * 返回许要的批配结果集(不区分大小写的正规表达式批配)
	 *
	 * @param source
	 *            批配的源字符串
	 *
	 * @param regexp
	 *            批配的正规表达式
	 *
	 * @return 如果源字符串符合要求返回许要的批配结果集{@link #getHardRegexpMatchResult(String, String) 使用方法参见getHardRegexpMatchResult(String, String)}
	 */
	public static ArrayList<String> getSoftRegexpArray(String source, String regexp)
	{
		List<String> tempList=new ArrayList<String>();

		try
		{
			//用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();
			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();
			//实例不区分大小写的正规表达式模板
			Pattern softPattern =  compiler.compile(regexp,Perl5Compiler.CASE_INSENSITIVE_MASK);

			if (matcher.contains(source, softPattern))
			{
				// 如果批配结果正确,返回取出的批配结果
				MatchResult matchResult=matcher.getMatch();
				for(int i=0;i<matchResult.length() && matchResult.group(i)!=null; i++)
				{
					tempList.add(i,matchResult.group(i));
				}
			}
		}
		catch (MalformedPatternException e)
		{
			e.printStackTrace();
		}
		return (ArrayList<String>)tempList;
}

  /**
   * <pre>
   * 得到指定分隔符中间的字符串的集合,
   *              说明: 1.分隔符为 ()，[]，{}，<> 中的一组
   *                        2.得到的集合以 ASCII 的升序排列
   *              如       String originStr="((([a]+[b])/[c])-24)+[d]";
   *              则          getStrBetweenSeparator(originStr,"[","]"));
   *                           返回结果集合有四个元素  [a, b, c, d],
   *                          以 ASCII 的升序排列
   * </pre>
   *
   * @param originStr
   *                         要提取的源字符串
   * @param leftSeparator
   *                         左边的分隔符
   * @param rightSeparator
   *                        右边的分隔符
   * @return
   *                    指定分隔符中间的字符串的集合
   */
  public static Set<String> getBetweenSeparatorStr(final String originStr,final char leftSeparator,final char rightSeparator)
  {
            Set<String>  variableSet=new TreeSet<String>();
            if(Util.isNull(originStr))
            {
                return variableSet;
            }
             String sTempArray[]=originStr.split("(\\"+leftSeparator+")");
             for(int i=1;i<sTempArray.length;i++)
             {
                         int endPosition= sTempArray[i].indexOf(rightSeparator);
                          String sTempVariable=sTempArray[i].substring(0,endPosition);
                          variableSet.add(sTempVariable);
             }
        return variableSet;
  }




	public static void main(String a[])
	{

        /*
		PatternCompiler compiler = new Perl5Compiler();
		PatternMatcher matcher = new Perl5Matcher();
		MatchResult matchResult=matcher.getMatch();
		String email = "wuzhi2000@hotmail.com.cn";
		String email_regexp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
		String url = "http://www";
		String url_regexp = "^(?:http|https|ftp)://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";// 匹配url
		String date = "1111-1-9";
		String date_regexp = "^[1-9]{1}[0-9]{0,3}[-\\s][0-9]{1,2}[-\\s][0-9]{1,2}$";// 匹配日期
		String phone = "010-1234567";
		String phone_regexp = "^(?:0[0-9]{2,4}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[0-9]{6,8}$";// 匹配电话
		String icon="/he//fff/aaaq34.gif";
		String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png)$|^\\w{1,}\\.(gif|dmp|png)$";
		String name="\"^";
		String number="023";
		String pic ="forum/head_icons/anoymous20050428125334.jpg";
		System.out.println(Regexp.isSoftRegexpValidate(pic,Regexp.icon_regexp));
          */

            /*
		  try
          {


             PatternCompiler compiler = new Perl5Compiler();
                PatternMatcher matcher = new Perl5Matcher();
                MatchResult matchResult=matcher.getMatch();

              //一个提取html标签属性的例子
              String regexpForFontTag="<\\s*font\\s+([^>]*)\\s*>";
              String  regexpForFontAttrib="([a-z]+)\\s*=\\s*\"([^\"]+)\"";

              String html=" <font face=\"Arial, serif\" size=\"+2\" color=\"red\">";
              System.out.println(regexpForFontTag);
              System.out.println(regexpForFontAttrib); System.out.println(html);

              Pattern	  p1=compiler.compile(regexpForFontTag,Perl5Compiler.CASE_INSENSITIVE_MASK);
              Pattern
              p2=compiler.compile(regexpForFontAttrib,Perl5Compiler.CASE_INSENSITIVE_MASK);
              System.out.println(matcher.contains(html,p1));
              if(matcher.contains(html,p1))
              {
                          MatchResult
                          result=matcher.getMatch(); System.out.println(result.group(1));
                          String attribs=result.group(1);

                          PatternMatcherInput input =new PatternMatcherInput(attribs);
                          System.out.println(matcher.contains(input,p2));
                          while(matcher.contains(input,p2))
                          {
                                   result=matcher.getMatch();
                                    System.out.println(result.group(1)+" : "+result.group(2));
                           }
              }

		  }
          catch(MalformedPatternException e)
          {
              e.printStackTrace();
		  }
              */

        /*
           //一个提取http的例子 String
		  try
          {
                 PatternCompiler compiler = new Perl5Compiler();
                 PatternMatcher matcher = new Perl5Matcher();
                 MatchResult matchResult=matcher.getMatch();
                  String http="http://www.suncer.com:8080/index.html?login=true";
                  String http_regexp="(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";
                  Pattern  p1=compiler.compile(http_regexp,Perl5Compiler.CASE_INSENSITIVE_MASK);
                  System.out.println(matcher.contains(http,p1));
                  if(matcher.contains(http,p1))
                  {
                      MatchResult  result=matcher.getMatch();
                      System.out.println(result.group(1));
                      String attribs=result.group(1);

                  for( int i=0;i <result.length() && result.group(i)!=null; i++)
                  {
                     System.out.println(i+" : "+result.group(i));
                  }
                }
		   }
          catch(MalformedPatternException e)
          {
              e.printStackTrace();
		   }
                   */

        //一个提取字符串中的中括号 [ ] 包含的字符的例子

                          String expression="((([a]+[b])/[c])-24)+[d]";
                           System.out.println( getBetweenSeparatorStr(expression,'[',']'));

	}
}  