import java.util.Scanner;
import java.util.regex.*;

public class Message {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Select\n1.SMS\n2.WhatsApp\n3.Email");

        int option=scanner.nextInt();

        switch (option){
            case 1:
                MessagingService SMSService=new SMSMessagingService();
                SMSService.sendMessage();
                break;
            case 2:
                MessagingService whatsAppService=new WhatsAppMessagingService();
                whatsAppService.sendMessage();
                break;
            case 3:
                MessagingService emailService=new EmailMessagingService();
                emailService.sendMessage();
                break;
        }
    }
}


interface MessagingService{
    void sendMessage();
}

class SMSMessagingService implements MessagingService{
    private String text="";
    @Override
    public void sendMessage() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the valid Mobile Number");
        long mobNum=scanner.nextLong();

        String numberString=String.valueOf(mobNum);
        int FirstNumber=Integer.parseInt(String.valueOf(numberString.charAt(0)));

        if((FirstNumber==6||FirstNumber==7||FirstNumber==8||FirstNumber==9) && numberString.length()==10){
            sendSMS(mobNum);
        }else{
            System.out.println("Invalid Mobile Number !!!!");
        }
    }

    private void sendSMS(long mobNum) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the SMS Message to send");
        text=scanner.nextLine();
        System.out.println("Message: ("+text+") is sent to "+mobNum+"\n");
    }
}

class EmailMessagingService implements MessagingService{
    private String text="";
    @Override
    public void sendMessage() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the valid Email Address");
        String email=scanner.nextLine();

        String regex="^[A-Za-z0-9+_.-]+@[A-Za-z-]+.[A-Za-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            sendEmail(email);
        }else{
            System.out.println("Invalid Email Address !!!");
        }
    }

    private void sendEmail(String email) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Email Message to send");
        text=scanner.nextLine();
        System.out.println("Message ("+text+") is sent to "+email+"\n");
    }
}

class WhatsAppMessagingService implements MessagingService{
    private String text="";
    @Override
    public void sendMessage() {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the valid Mobile Number");
        long mobNum=scanner.nextLong();

        String numberString=String.valueOf(mobNum);
        int FirstNumber=Integer.parseInt(String.valueOf(numberString.charAt(0)));

        if((FirstNumber==6||FirstNumber==7||FirstNumber==8||FirstNumber==9) && numberString.length()==10){
            System.out.println("WhatsApp available ? Type true or false");
            boolean isWhatsApp=scanner.nextBoolean();
            sendWhatsApp(mobNum,isWhatsApp);
        }else{
            System.out.println("Invalid Mobile Number !!!!");
        }
    }

    private void sendWhatsApp(long mobNum, boolean isWhatsApp) {
        Scanner scanner=new Scanner(System.in);
        if(isWhatsApp){
            System.out.println("Enter the WhatsApp Message to send");
            text=scanner.nextLine();
            System.out.println("Message: ("+text+") is sent to "+mobNum+"\n");
        }
        else{
            System.out.println("whatsApp unavailable !!!");
        }
    }
}