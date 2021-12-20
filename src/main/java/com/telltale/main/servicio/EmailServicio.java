package com.telltale.main.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServicio {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromMail;

    private static final String SUBJECT = "¡Bienvenido a la comunidad de TellTale: Historia x Historia!";

    @Async
    public void sendMail(String username, String mailTo) throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMessage = "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n" +
                "    <meta name=\"description\" content=\"\" />\n" +
                "    <meta name=\"author\" content=\"\" />\n" +
                "    <title>¡Bienvenido a TellTale (Historia x Historia)!</title>\n" +
                "    <!-- Font Awesome icons (free version)-->\n" +
                "    <script src=\"https://kit.fontawesome.com/8827b26416.js\" crossorigin=\"anonymous\"></script>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css\">\n" +
                "    <!-- Google fonts-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\" />\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i\" rel=\"stylesheet\" />\n" +
                "    <!-- Core theme CSS (includes Bootstrap)-->\n" +
                "    <!-- jQuery Library -->\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                "    <!-- Counts JS -->\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js\"></script>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js\"></script>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n" +
                "</head>\n" +
                "<body style=\"background-color: #B4F0CB;\">\n" +
                "\n" +
                "    <div class=\"container\">\n" +
                "        <section name=\"header\" id=\"header\">\n" +
                "            <header class=\"text-center text-faded d-none d-lg-block mb-5\">\n" +
                "                <img src=\"https://drive.google.com/uc?export=view&id=1nazW5FipBpp5elqAkFRwLaEP96IeWmG5\" />\n" +
                "                <h1 class=\"text-center text-faded\">\n" +
                "                    <span class=\"site-heading-upper\">HISTORIA <span class=\"text-secondary\">X</span> HISTORIA</span>\n" +
                "                </h1>\n" +
                "                <br>\n" +
                "                <hr>\n" +
                "                <br>\n" +
                "            </header>\n" +
                "        </section>\n" +
                "    \n" +
                "        <section name=\"emailbody\" id=\"emailbody\">\n" +
                "            <div class=\"container\">\n" +
                "                <div class=\"col-xl-6 mx-auto\">\n" +
                "                    <div class=\"card text-center\">\n" +
                "                        <div class=\"card-header\">\n" +
                "                            <h3>\n" +
                "                                ¡Bienvenidx a la comunidad!\n" +
                "                            </h3>\n" +
                "                            <h2 class=\"text-danger mb-4\">\n" +
                "                                TellTale: Historia X Historia\n" +
                "                            </h2>    \n" +
                "                        </div>\n" +
                "                        <div class=\"card-body\">\n" +
                "                            <div col-3>\n" +
                "                                <img src=\"https://drive.google.com/uc?export=view&id=1I0rfweYN36MFssLK8DfIDvcf0RTQS3yu\" alt=\"Avatar\" class=\"rounded-circle\">\n" +
                "                            </div>\n" +
                "                            <br>\n" +
                "                            <h4 class=\"card-title\">Hola </h4>\n" +
                "                            <h4 class=\"text-danger mb-4\">@"+ username + "</h4>\n" +
                "                            <br>\n" +
                "                            <h6>\n" +
                "                                Queremos agradecerte por formar parte de esta gran comunidad que estamos armando.\n" +
                "                            </h6>\n" +
                "                            <h6>\n" +
                "                                Esperamos te sientas a gusto y disfrutes de esta experiencia única.\n" +
                "                            </h6>\n" +
                "                            <br>\n" +
                "                            <h5 class=\"card-text p-2\">\n" +
                "                                <b>¿¡Ya estás preparadx para compartir tu historia!?</b>\n" +
                "                            </h5>\n" +
                "                            <br>\n" +
                "                            <a href=\"http://localhost:8080/login\" class=\"btn btn-success mb-1\">¡Quiero contar mi historia!</a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            </div>\n" +
                "            </section>   \n" +
                "<br>    \n" +
                "<br>    \n" +
                "        <section name=\"footer\" id=\"footer\">\n" +
                "            <footer class=\"bg-dark text-faded text-center py-5\">\n" +
                "                <div class=\"container\">\n" +
                "                    <h4 class=\"m-0 small text-white\">Copyright &copy; TellTale 2021</h4>\n" +
                "                </div>\n" +
                "                <!-- Jquery -->\n" +
                "                <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\" integrity=\"sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\"></script>\n" +
                "                <!-- Bootstrap core JS-->\n" +
                "                <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                "            </footer>\n" +
                "        </section>\n" +
                "        </div>\n" +
                "\n" +
                "</body>\n";
        mimeMessageHelper.setText(htmlMessage, true);
        mimeMessageHelper.setTo(mailTo);
        mimeMessageHelper.setFrom(fromMail);
        mimeMessageHelper.setSubject(SUBJECT);
        sender.send(mimeMessage);
    }

/*
    @Async
    public void sendMail(String username, String mailTo) {
        SimpleMailMessage message = new SimpleMailMessage(); //si vamos a enviar un HTML o imágenes hay que usar "MimeMessage message = new MimeMessage();"
        message.setTo(mailTo);
        message.setFrom(fromMail);
        message.setSubject(SUBJECT);
        message.setText("¡Hola "+ username + "!\n¡Bienvenidx a nuestra comunidad!\n¡Gracias por registrarte!\n¿Ya estás listx para escribir tu primer historia?");
        sender.send(message);
    }
*/

/*
    //este método produce otro hilo para enviar el mail. Funciona de manera asíncrona pero sin utilizar notaciones Spring
    public void sendMailByThread(String username, String mailTo) {
        new Thread(() -> {
            SimpleMailMessage message = new SimpleMailMessage(); //si vamos a enviar un HTML o imágenes hay que usar "MimeMessage message = new MimeMessage();"
            message.setTo(mailTo);
            message.setFrom(fromMail);
            message.setSubject(SUBJECT);
            message.setText("¡Hola "+ (username.toUpperCase()) + "!\n¡Bienvenido a nuestra página!\n¡Gracias por registrarte!");
            sender.send(message);
        }).start();
    }
*/

}
