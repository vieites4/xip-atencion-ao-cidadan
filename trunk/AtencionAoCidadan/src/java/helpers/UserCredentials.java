/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import model.Usuario;

/**
 *
 * @author joseangel.pineiro
 */
public class UserCredentials {

    public static String NUMEROS = "0123456789";
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static String ESPECIALES = "ñÑ";
    
    private String username;
    private String password;

    private UserCredentials() {
    }

    public static UserCredentials forUser(Usuario u) {
        UserCredentials c = new UserCredentials();
        c.setUsername(generateUsername(u));
        c.setPassword(generatePassword(u));
        return c;

    }

    private static String generateUsername(Usuario u) {
        return replaceSpecial(u.getCiudadano().getNombre().toLowerCase().trim().replace(" ", ".") + "." + u.getCiudadano().getApellidos().toLowerCase().trim().replace(" ", "."));
    }

    private static String generatePassword(Usuario u) {
        return getPassword(6);
    }

    public static String replaceSpecial(String input) {

        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    public static String getPassword(int length) {
            return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
        }

        public static String getPassword(String key, int length) {
            String pswd = "";

            for (int i = 0; i < length; i++) {
                pswd += (key.charAt((int) (Math.random() * key.length())));
            }

            return pswd;
        }
}
