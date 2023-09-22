import java.util.*;
import java.io.*;

class Packer
{
    public static void main(String args[]) throws Exception
    {
        int iRet = 0;
        byte Buffer[] = new byte[1024];
        String Header = null;
        int PackCount = 0;
        Scanner sobj = new Scanner(System.in);

        System.out.println("-------------------Marvellous Packer Unpacker-------------------");
        System.out.println("Packing Activity of the application has started...");
        System.out.println("Enter the name of folder which contains files that you want to pack");
        String FolderName = sobj.nextLine();
        
        File fobj = new File(FolderName);
       

        System.out.println("Enter the name of the packed file that you want to create");
        String PackFile = sobj.nextLine();

        try 
        {
            File Packobj = new File(PackFile);
            boolean bRet = Packobj.createNewFile();
            if(bRet == false)
            {
                System.out.println("Unable to create file");
                return;
            }

            System.out.println("Packed file successfully gets created");

            FileOutputStream outobj = new FileOutputStream(Packobj);

            bRet = fobj.isDirectory();
            if(bRet == true)
            {
                File list[] = fobj.listFiles();
                 System.out.println("Total numbe of files found in the directory are: "+list.length);

                for(int i = 0; i < list.length;i++)
                {
                    if((list[i].getName()).endsWith(".txt"))
                    {
                        Header = list[i].getName()+" "+list[i].length();
                        for(int j =  Header.length(); j < 100; j++)
                        {
                            Header = Header + " ";
                        }

                        byte bHeader[] = Header.getBytes();
                        outobj.write(bHeader,0,bHeader.length);

                        FileInputStream inobj = new FileInputStream(list[i]);

                        //loop to write the data
                        while((iRet = inobj.read(Buffer)) != -1)
                        {
                            outobj.write(Buffer,0,iRet);
                        } 

                        System.out.println("File Successfullt packed with name"+list[i].getName());
                        inobj.close();
                        PackCount++;
                    }
                }
                System.out.println("---------------Packing Summary---------------");
                System.out.println("Total number of files scanned "+list.length);
                System.out.println("Total number of files packed "+PackCount);

                System.out.println("Thank You for using Marvelloust Packer-Unpacker...");
            }
        }
        catch (Exception iobj)
        {
            System.out.println("Exception occured:"+iobj);
        }     
    }
}