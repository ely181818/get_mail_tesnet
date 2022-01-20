package get_mail.get_mail;


import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.imap.IMAPFolder;

public class CheckingMails {

     public static String  host;// = "imap.googlemail.com";
     
     public static  String username;// ;// change accordingly
     public static  String password ;// change accordingly
     public static  String subjectMail;
	
     CheckingMails(String  host,String username,String password){
    	 
    	 this.host=host;
    	 this.username=username;
    	 this.password=password;
    	 
    	 
     }
public static Message check(String host,  String user,
  String password,String subjectMail) 
{
  try {
	  
	  IMAPFolder folder = null;
      Store store = null;
      String subject = null;
      Flag flag = null;
	  
	  Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");

      Session session = Session.getDefaultInstance(props, null);

      store = session.getStore("imaps");
      store.connect(host,user, password);

     
      folder = (IMAPFolder) store.getFolder("inbox"); 
     
      if(!folder.isOpen())
      folder.open(Folder.READ_WRITE);
      Message[] messages =folder.getMessages();
      System.out.println("messages.length---" + messages.length);

  for (int i = 0, n = messages.length; i < n; i++) {
	  System.out.println("Email Number Tested" + (i + 1));
     Message message = messages[i];
     System.out.println("Subject: " + message.getSubject());
     System.out.println("From: " + message.getFrom()[0]);
     System.out.println("Text: " + getTextFromMessage( messages[i]));
     if(message.getSubject() !=null) {
	   if(message.getSubject().contains(subjectMail)) {
	     System.out.println("---------------------------------");
	     System.out.println("Subject: " + message.getSubject());
	     System.out.println("From: " + message.getFrom()[0]);
	     System.out.println("Text: " + getTextFromMessage( messages[i]));
	     return messages[i];
	    
	    
	   }
     }


  }

 
  folder.close(false);
  store.close();

  } 
  
  catch (NoSuchProviderException e) {
     e.printStackTrace();
  } catch (MessagingException e) {
     e.printStackTrace();
  } catch (Exception e) {
     e.printStackTrace();
  }
return null;
}

public static Message checkMailSubject(String subjectMail) 




		{
	
	
		  try {
			  
			  IMAPFolder folder = null;
		      Store store = null;
		      String subject = null;
		      Flag flag = null;
			  
			  Properties props = System.getProperties();
		      props.setProperty("mail.store.protocol", "imaps");

		      Session session = Session.getDefaultInstance(props, null);

		      store = session.getStore("imaps");
		      store.connect(host,username, password);

		     
		      folder = (IMAPFolder) store.getFolder("inbox"); 
		     
		      if(!folder.isOpen())
		      folder.open(Folder.READ_WRITE);
		      Message[] messages =folder.getMessages();
		      System.out.println("messages.length---" + messages.length);

		  for (int i = 0, n = messages.length; i < n; i++) {
			  System.out.println("Email Number Tested" + (i + 1));
		     Message message = messages[i];
		  /*   System.out.println("Subject: " + message.getSubject());
		     System.out.println("From: " + message.getFrom()[0]);
		     System.out.println("Text: " + getTextFromMessage( messages[i]));*/
		     if(message.getSubject() !=null) {
			    if(message.getSubject().contains(subjectMail)) {
			     System.out.println("---------------------------------");
			     System.out.println("Subject: " + message.getSubject());
			     System.out.println("From: " + message.getFrom()[0]);
			     System.out.println("Text: " + getTextFromMessage( messages[i]));
			     return messages[i];
			    
			    
			   }
		     }


		  }

		 
		  folder.close(false);
		  store.close();

		  } 
		  
		  catch (NoSuchProviderException e) {
		     e.printStackTrace();
		  } catch (MessagingException e) {
		     e.printStackTrace();
		  } catch (Exception e) {
		     e.printStackTrace();
		  }
		return null;
		}

public static Message checkMailFrom(String From) 



{


  try {
	  
	  IMAPFolder folder = null;
      Store store = null;
      String subject = null;
      Flag flag = null;
	  
	  Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");

      Session session = Session.getDefaultInstance(props, null);

      store = session.getStore("imaps");
      store.connect(host,username, password);

     
      folder = (IMAPFolder) store.getFolder("inbox"); 
     
      if(!folder.isOpen())
      folder.open(Folder.READ_WRITE);
      Message[] messages =folder.getMessages();
      System.out.println("messages.length---" + messages.length);

  for (int i = 0, n = messages.length; i < n; i++) {
	  System.out.println("Email Number Tested" + (i + 1));
     Message message = messages[i];
  /*   System.out.println("Subject: " + message.getSubject());
     System.out.println("From: " + message.getFrom()[0]);
     System.out.println("Text: " + getTextFromMessage( messages[i]));*/
     
     if(message.getSubject() !=null) {
	    if(message.getSubject().contains(From)) {
	     System.out.println("---------------------------------");
	     System.out.println("Subject: " + message.getSubject());
	     System.out.println("From: " + message.getFrom()[0]);
	     System.out.println("Text: " + getTextFromMessage( messages[i]));
	     return messages[i];
	    
	    
	   }
     }


  }

 
  folder.close(false);
  store.close();

  } 
  
  catch (NoSuchProviderException e) {
     e.printStackTrace();
  } catch (MessagingException e) {
     e.printStackTrace();
  } catch (Exception e) {
     e.printStackTrace();
  }
return null;
}
private  static String getTextFromMessage(Message message) throws MessagingException, IOException {
    String result = "";
    if (message.isMimeType("text/plain")) {
        result = message.getContent().toString();
    } else if (message.isMimeType("multipart/*")) {
        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
        result = getTextFromMimeMultipart(mimeMultipart);
    }
    return result;
}


private static String getTextFromMimeMultipart(
        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
    String result = "";
    int count = mimeMultipart.getCount();
    for (int i = 0; i < count; i++) {
        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
        if (bodyPart.isMimeType("text/plain")) {
            result = result + "\n" + bodyPart.getContent();
            break; // without break same text appears twice in my tests
        } else if (bodyPart.isMimeType("text/html")) {
            String html = (String) bodyPart.getContent();
            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart){
            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }
    }
    return result;
}
public static void main(String[] args) {

  String host = "imap.googlemail.com";
 
  String username = "******@gmail.com ";// change accordingly
  String password = "*****";// change accordingly
  String subjectMail="What's new in  GFI EventsManager™ 2012 and  GFI EndPointSecurity™ 2012?";// change the subject you whant to find accordingly
  check(host, username, password,subjectMail);

}

}