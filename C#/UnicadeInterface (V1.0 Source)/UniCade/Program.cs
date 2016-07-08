﻿using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;

namespace UniCade
{
    class Program
    {
        public static Database dat;
        
        public static string databasePath = Directory.GetCurrentDirectory() + @"\Database.txt";
        public static string romPath = @"C:\UniCade\ROMS";
        public static string mediaPath = @"C:\UniCade\Media";
        public static string emuPath = @"C:\UniCade\Emulators";
        public static string prefPath = Directory.GetCurrentDirectory() + @"\Preferences.txt";
        public static int coins = 0;
        public static bool playtimeRemaining = true;
        public static string userLicenseName;
        public static string userLicenseKey;
        public static bool validLicense = true;
        [System.STAThreadAttribute]

        public static void Main(string[] args)
        {

            //MessageBox.Show(Directory.GetCurrentDirectory());

            dat = new Database();
            
            if (!FileOps.loadPreferences(prefPath))
            {
                
                
                FileOps.defaultPreferences();
                FileOps.savePreferences(prefPath);
                NotificationWindow nfw = new NotificationWindow("WARNING", "Preference file not found.\n Loading defaults...");
                nfw.Show();
            }

            if (!Directory.Exists(romPath))
            {
                Directory.CreateDirectory(romPath);
                FileOps.createNewROMdirectory();
                //MessageBox.Show("ROM directory not found. Creating new directory structure");

            }
            if (!Directory.Exists(emuPath))
            {
                Directory.CreateDirectory(emuPath);
                FileOps.createNewEmudirectory();
                //MessageBox.Show("Emulator directory not found. Creating new directory structure");
            }

            if (!FileOps.VerifyMediaDirectory())
            {
                return;
            }
                
            

            if (SettingsWindow.curUser == null)
            {
                SettingsWindow.curUser = new User("UniCade", "temp", 0, "unicade@unicade.com", 0, " ", "", "");
            }


            if (!ValidateSHA256(userLicenseName + Database.getHashKey(), userLicenseKey))
            {
                validLicense = false;


            }

            if (!FileOps.loadDatabase(databasePath))
            {

                FileOps.loadDefaultConsoles();
                FileOps.scan(romPath);
                try
                {
                    FileOps.saveDatabase(databasePath);
                }
                catch
                {
                    MessageBox.Show("Error Saving Database");
                }
                NotificationWindow nfw = new NotificationWindow("WARNING", "Database file not found.\n Loading defaults...");
                nfw.Show();
            }


            var app = new App();
            app.InitializeComponent();
            app.Run();



        }



       public static string displayGameInfo(Game g)
        {
            string txt = "";
                txt = txt + ("\nTitle: " + g.getTitle() + "\n");
                txt = txt + ("\nRelease Date: " + g.getReleaseDate() + "\n");
                txt = txt + ("\nConsole: " + g.getConsole() + "\n");
                txt = txt + ("\nLaunch Count: " + g.launchCount.ToString() + "\n");
                txt = txt + ("\nDeveloper: " + g.getDeveloper() + "\n");
                txt = txt + ("\nPublisher: " + g.getPublisher() + "\n");
                txt = txt + ("\nPlayers: " + g.getPlayers() + "\n");
                txt = txt + ("\nCritic Score: " + g.getCriticScore() + "\n");
                txt = txt + ("\nESRB Rating: " + g.getTags() + "\n");
                txt = txt + ("\nESRB Descriptors: " + g.getEsrbDescriptor() + "\n");
                txt = txt + ("\nGame Description: " + g.getDescription() + "\n");
                return txt;
            }


        public static string SHA256Hash(string data)
        {
            if(data == null)
            {
                return null;
            }
            SHA256Managed sha256 = new SHA256Managed();
            byte[] hashData = sha256.ComputeHash(Encoding.Default.GetBytes(data));
            StringBuilder returnValue = new StringBuilder();

            for (int i = 0; i < hashData.Length; i++)
            {
                returnValue.Append(hashData[i].ToString());
            }
            return returnValue.ToString();
        }


        public static bool ValidateSHA256(string input, string storedHashData)
        {
            string getHashInputData = SHA256Hash(input);
            if (string.Compare(getHashInputData, storedHashData) == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }





    }

    }