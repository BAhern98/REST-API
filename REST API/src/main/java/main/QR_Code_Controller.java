/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Breweries;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/code")
public class QR_Code_Controller {

    @Autowired
    Breweries_Service service;

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getQRCode(@PathVariable("id") int id) throws WriterException, IOException {
        Breweries brewerie = service.getBrewerieById(id);
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(brewerie.getWebsite(), BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage code = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(code, "jpeg", baos);
        baos.flush();
        byte[] codeInBytes = baos.toByteArray();
        baos.close();
        
        Base64.Encoder encoder = Base64.getEncoder();
        String encoding = "data:image/png;base64," + encoder.encodeToString(codeInBytes);
        String output = "<html><body><img src="+encoding+" alt=\"QR Code\" height=\"200\" width=\"200\"></body></html>";
        return output;
    }
}
