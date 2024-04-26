import CryptoJs from "crypto-js";

class UserService {
  getPrivateKey() {
    return CryptoJs.SHA256("workNestPr1vateK3y");
  }

  encryptPassword(pwd) {
    var data = pwd.slice();
    var encryptedString = CryptoJs.AES.encrypt(data, this.getPrivateKey(), {
      iv: CryptoJs.enc.Base64.parse(""),
      mode: CryptoJs.mode.CBC,
      padding: CryptoJs.pad.Pkcs7,
    });
    return encryptedString.toString();
  }
  decryptPassword(encrypted) {
    var decrypted = CryptoJs.AES.decrypt(encrypted, this.getPrivateKey(), {
      iv: CryptoJs.enc.Base64.parse(""),
      mode: CryptoJs.mode.CBC,
      padding: CryptoJs.pad.Pkcs7,
    });
    return decrypted.toString(CryptoJs.enc.Utf8);
  }
}

export default new UserService();
