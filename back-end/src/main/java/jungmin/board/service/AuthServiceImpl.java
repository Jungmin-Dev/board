package jungmin.board.service;

import jungmin.board.domain.Info;
import jungmin.board.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Log
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final JavaMailSender javaMailSender;

    @Override
    public int join(Info param) throws Exception {
        // 회원가입
        return authMapper.userJoin(param);
    }

    @Override
    public int Certification(Info info) throws Exception {
        int result = 0;
        Optional<Info> check = authMapper.userEmailCertificationCheck(info);

        if(check.isPresent() && check.get().getSelfAuth().equals(info.getSelfAuth())){
            result = authMapper.userEmailCertificationDelete(info);
        }
        return result;
    }

    @Override
    public String mailCheck(Info info) throws Exception {


        String ePw = createKey();
        String FROM_ADDRESS = "jungminkim96@naver.com";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress(FROM_ADDRESS,"uni-core"));
        mimeMessage.addRecipients(Message.RecipientType.TO, info.getUserEmail());//보내는 대상
        mimeMessage.setSubject("회원가입 이메일 인증");
        String msgg="";

        msgg+= "<div style='margin:5px;'>";
        msgg+= "<h2> 안녕하세요 uni-core입니다. </h2>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";

        mimeMessage.setText(msgg, "utf-8", "html");//내용

        javaMailSender.send(mimeMessage);

        info.setSelfAuth(ePw);

        authMapper.userEmailCertification(info);

        return "1";
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }


    @Override
    public Optional duplicate(String param) throws Exception {
        // 중복 체크
        return Optional.ofNullable(authMapper.userDuplicate(param));
    }


    @Override
    public Optional login(Info param) throws Exception {
        return Optional.ofNullable(authMapper.userLogin(param));
    }
}
