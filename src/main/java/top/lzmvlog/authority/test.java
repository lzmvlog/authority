package top.lzmvlog.authority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import top.lzmvlog.authority.exception.TokenException;
import top.lzmvlog.authority.util.jwt.JwtUtil;

/**
 * @author ShaoJie
 * @Date 2020年06月03 10:12
 * @Description:
 */
public class test {
    /**
     * 签名密钥
     */
    @Value("${outh..token.signingKey}")
    public static String signingKey;

    @Autowired
    public JwtUtil jwtUtil;

    @Test
    public void test() {
        System.out.println(jwtUtil.createToken("1591313226"));
    }

    public static void main(String[] args) {
//        System.out.println(new DateTime());
//
//        Calendar calendar = Calendar.getInstance();
//        /* HOUR_OF_DAY 指示一天中的小时 */
//        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("一个小时前的时间：" + cn.hutool.core.date.DateUtil.date(calendar.getTime()));
//        System.out.println("当前的时间：" + df.format(new Date()));

//        System.out.println(signingKey);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiMTI3MjZjZTI3Njc0NjFlOTY0MWRhNjNmMDY1YjAyYyIsInN1YiI6IjE1OTEzMTMyMjYiLCJleHAiOjE1OTE0Mjc4MDEsImlhdCI6MTU5MTQyNDIwMX0.guaqUexbZ3Kydie2Q477Z0fFLeIKArhIO72PC38nekQ";
        Claims claims = Jwts.parser().setSigningKey("YXV0aG9yaXR5").parseClaimsJws(token).getBody();
        if (claims.equals(null))
            throw new TokenException();
        System.out.println(claims.toString());
        System.out.println(claims.getSubject());

    }
}
