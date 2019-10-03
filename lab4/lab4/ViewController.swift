//
//  ViewController.swift
//  lab4
//
//  Created by Peter Rosental on 02/10/2019.
//  Copyright © 2019 Peter Rosental. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    
    @IBOutlet weak var preTipTotal: UITextField!
    @IBOutlet weak var tipPercentLabel: UILabel!
    @IBOutlet weak var tipValueLabel: UILabel!
    @IBOutlet weak var tipPercent: UIStepper!
    
    @IBAction func changeTip(_ sender: UIStepper) {
        updateTip()
        tipPercentLabel.text = String(format: "%.0f", tipPercent.value) + "% Tip"
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        updateTip()
        textField.resignFirstResponder()
        return true
    }
    
    func updateTip() {
        let percent: Float = Float(tipPercent.value)
        var pretip:  Float
        
        if preTipTotal.text!.isEmpty {
            pretip = 0
        } else if Float(preTipTotal.text!) != nil {
            pretip = Float(preTipTotal.text!)!
        } else {
            pretip = 0
            let alert = UIAlertController(title: "Warning", message: "Can't calculate a tip of not a number. Please enter a number", preferredStyle: UIAlertController.Style.alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: nil)
            let okAction = UIAlertAction(title: "Okay", style: UIAlertAction.Style.default, handler: {action in
                self.preTipTotal.text = "0"
                self.updateTip()
            })
            alert.addAction(cancelAction)
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
        
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle = NumberFormatter.Style.currency
        tipValueLabel.text = currencyFormatter.string(from: NSNumber(value: pretip * percent * 0.01))
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        preTipTotal.delegate = self
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard() {
        updateTip()
        view.endEditing(true)
    }
}

