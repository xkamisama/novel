package novel;

import org.junit.Test;

import com.xkami.util.DesUtil;
import com.xkami.util.RegexUtil;

import junit.framework.TestCase;

public class Base64Test extends TestCase {
	@Test
	public void test() {
		String data = "你是个大傻瓜fgvhcnbvnvnmv";
		String key = "ahgfnbcbnvcbnvc";
		try {
			String encode = DesUtil.encrypt(data, key);
			System.out.println(encode);
			String decode = DesUtil.decrypt(encode, key);
			System.out.println(decode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testRegex() {
		
	}
	
}
