using System;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;

namespace LicenseGenerator
{
    public partial class Gui : Form
    {
        #region Properties

        /// <summary>
        /// The private hash key for the UniCade interface
        /// </summary>
        private const string UniCadeKey = "JI3vgsD6Nc6VSMrNw0b4wvuJmDw6Lrld";

        /// <summary>
        /// The private hash key for the gust list software
        /// </summary>
        private const string GuestListKey = "bjx58tQvp2Y1U5xO71PIsvFL7FGyyI08";

        #endregion

        #region  Public Methods

        /// <summary>
        /// Public constructor for the GUI
        /// </summary>
        public Gui()
        {
            //Initialize the GUI window
            InitializeComponent();

            //Populate combo boxes
            ProductComboBox.Items.Add("UniCade Interface");
            ProductComboBox.Items.Add("Guest List Software");
            EncryptionComboBox.Items.Add("SHA1");
            EncryptionComboBox.Items.Add("SHA256");

            //Change the selected indexes
            ProductComboBox.SelectedIndex = 0;
            EncryptionComboBox.SelectedIndex = 1;
        }

        #endregion

        #region  Private Methods

        /// <summary>
        /// Generate a new license key
        /// </summary>
        private void GenerateButtonClick(object sender, EventArgs e)
        {
            if ((TextboxUsername.Text.Length < 1) || (TextboxSecretKey.Text.Length < 1)  || (EncryptionComboBox.SelectedItem==null))
            {
                MessageBox.Show(Strings.RequiredFieldsEmpty);
                return;
            }
            if (EncryptionComboBox.SelectedItem.ToString().Equals("SHA1"))
            {
                TextboxLicenseKey.Text = Sha1Hash(TextboxUsername.Text + TextboxSecretKey.Text);
            }
            else if (EncryptionComboBox.SelectedItem.ToString().Equals("SHA256"))
            {
                TextboxLicenseKey.Text = Sha256Hash(TextboxUsername.Text + TextboxSecretKey.Text);
            }
        }

        /// <summary>
        /// Exit the program
        /// </summary>
        private void CloseButtonClick(object sender, EventArgs e)
        {
            Close();
        }

        private void ValidateButtonClick(object sender, EventArgs e)
        {
            if ((TextboxUsername.Text.Length < 1) || (TextboxSecretKey.Text.Length < 1) || (TextboxLicenseKey.Text.Length < 1) || (EncryptionComboBox.SelectedItem == null))
            {
                MessageBox.Show(Strings.RequiredFieldsEmpty);
                return;
            }
            if (EncryptionComboBox.SelectedItem.ToString().Equals("SHA1"))
            {
                MessageBox.Show(ValidateSha1((TextboxUsername.Text + TextboxSecretKey.Text), TextboxLicenseKey.Text)
                    ? Strings.KeyValid
                    : Strings.KeyInvalid);
            }
            else if (EncryptionComboBox.SelectedItem.ToString().Equals("SHA256"))
            {
                MessageBox.Show(ValidateSha256((TextboxUsername.Text + TextboxSecretKey.Text), TextboxLicenseKey.Text)
                    ? Strings.KeyValid
                    : Strings.KeyInvalid);
            }
        }

        /// <summary>
        /// Method called when the selected product is changed
        /// </summary>
        private void ProductMenuSelectedIndexChanged(object sender, EventArgs e) 
        {
            switch (ProductComboBox.SelectedIndex)
            {
                case 0:
                    TextboxSecretKey.Text = UniCadeKey;
                    break;
                case 1:
                    TextboxSecretKey.Text = GuestListKey;
                    break;
                default:
                    TextboxSecretKey.Text = null;
                    break;
            }
        }

        #endregion

        #region Helper Methods

        /// <summary>
        /// Generate a new string with the SHA1 hash algorithim
        /// </summary>
        private static string Sha1Hash(string data)
        {
            SHA1 sha1 = SHA1.Create();
            byte[] hashData = sha1.ComputeHash(Encoding.Default.GetBytes(data));
            StringBuilder returnValue = new StringBuilder();

            foreach (byte t in hashData)
            {
                returnValue.Append(t.ToString());
            }
            return returnValue.ToString();
        }

        /// <summary>
        /// Generate a new string with the SHA256 hash algorithim
        /// </summary>
        private static string Sha256Hash(string data)
        {
            SHA256Managed sha256 = new SHA256Managed();
            byte[] hashData = sha256.ComputeHash(Encoding.Default.GetBytes(data));
            StringBuilder returnValue = new StringBuilder();

            foreach (byte t in hashData)
            {
                returnValue.Append(t.ToString());
            }
            return returnValue.ToString();
        }

        /// <summary>
        /// Validate a string against a SHA1 hash key
        /// </summary>
        private static bool ValidateSha1(string input, string storedHashData)
        {
            string getHashInputData = Sha1Hash(input);

            return string.CompareOrdinal(getHashInputData, storedHashData) == 0;
        }

        /// <summary>
        /// Validate a string against a SHA257 hash key
        /// </summary>
        private static bool ValidateSha256(string input, string storedHashData)
        {
            string getHashInputData = Sha256Hash(input);

            return string.CompareOrdinal(getHashInputData, storedHashData) == 0;
        }

        #endregion
    }
}
