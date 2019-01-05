package app.calcounter.com.individualproject3.Controller;


// simple login validation
// should replace with firebase login etc


public class LoginValidation {

    public boolean validateLogin(String u, String p)
    {
        if(u.equals(""))
        {
            return false;
        }

        if(p.equals(""))
        {
            return false;
        }

        if(true) // mUsers.contains(u) against the database
        {
            if(true)
            {
                // check if password in datasete
                return true; // allow login
            }

            return false;
        }

        return false;
    }

    public boolean addUser(String u, String p){

        if(p.equals(""))
        {
            return false;
        }

        if(p.length() < 6)
        {
            return false;
        }

        if(true){ // validate if user already there both child and adult
            //ToDo
            return false;
        }
        else
        {
            //Todo
            // add adult username
            // add adult password
            // add child username
            // add child password
            return true;
        }
    }


    public int validNonBlank(String adultFirstName, String adultLastName, String adultDOB, String adultEmail, String adultUsername,
    String adultPassword, String childFirstName, String childUsername, String childPassword )
    {
        if(adultFirstName.equals(""))
        {
            return -1;
        }

        if(adultLastName.equals(""))
        {
            return -2;
        }

        if(adultDOB.equals(""))
        {
            return -3;
        }

        if(adultEmail.equals(""))
        {
            return -4;
        }

        if(adultUsername.equals(""))
        {
            return -5;
        }

        if(adultPassword.equals(""))
        {
            return -6;
        }

        if(childFirstName.equals(""))
        {
            return -7;
        }

        if(childUsername.equals(""))
        {
            return -8;
        }

        if(childPassword.equals(""))
        {
            return -9;
        }

        return 0; // 0 is success
    }

    public boolean validNames(String n){

        if(n.length() < 4)
        {
            return false;
        }

        if(n.length() > 30)
        {
            return false;
        }

        return true;
    }

    public boolean validEmail(String em)
    {
        if(em.length() < 6)
        {
            return  false;
        }

        if(em.length() > 70)
        {
            return false;
        }

        if(!em.contains("@"))
        {
            return false;
        }

        if(!em.contains("."))
        {
            return false;
        }

        return true;
    }


    // need to check this with a valid password
    // need to check this with a valid password
    // need to check this with a valid password

    public boolean validUnPass(String u, String p)
    {
        if(u.length() < 4)
        {
            return false;
        }

        if(p.length() < 4)
        {
            return false;
        }

        if(u.length() > 20)
        {
            return false;
        }

        if(p.length() > 20)
        {
            return false;
        }

        if(p.equals(p.toLowerCase())){
            return false;
        }


        return true;
    }

}
