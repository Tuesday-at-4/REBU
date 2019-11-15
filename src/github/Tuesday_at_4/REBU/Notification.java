/***********************************************************
 * File   : Notification.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : A class for storing the text and notification type for each notification pulled from the DB
 * NOTE: A notification is made whenever a Ride status change occurs.
 ************************************************************/
package github.Tuesday_at_4.REBU;

public class Notification {
  private int notificationType;
  private String notificationText;

  public Notification(int notificationType, String notificationText){
    this.notificationText = notificationText;
    this.notificationType = notificationType;
  }
  public int getNotificationType() {
    return notificationType;
  }

  public String getNotificationText() {
    return notificationText;
  }
}
