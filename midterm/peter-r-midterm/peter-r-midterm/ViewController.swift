//
//  ViewController.swift
//  peter-r-midterm
//
//  Created by Peter Julian Rosenthal on 10/15/19.
//  Copyright © 2019 Peter Julian Rosenthal. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var milesTextField: UITextField!
    @IBOutlet weak var totalCommuteTimeLabel: UILabel!
    @IBOutlet weak var gasToPurchaseLabel: UILabel!
    @IBOutlet weak var commuteTypeControl: UISegmentedControl!
    @IBOutlet weak var currentGasLabel: UILabel!
    @IBOutlet weak var commuteTypeImage: UIImageView!
    
    private let averageCarSpeed: Float = 20
    private let averageCarMpg: Float = 24
    private let averageBikeSpeed: Float = 10
    private let averageBusSpeed: Float = 12
    private let busWaitTime: Float = 5
    private var daysToCompute: Float = 1
    private var currentGas: Float = 10.5
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func changeCurrentGas(_ sender: UISlider) {
        currentGas = sender.value
        currentGasLabel.text = String(format: "%.3f", currentGas) + " gallons"
    }
    
    @IBAction func commuteButtonPressed(_ sender: UIButton) {
        computeCommute()
    }
    
    @IBAction func monthlyCommuteSwitch(_ sender: UISwitch) {
        if (sender.isOn) {
            daysToCompute = 20
        }
        else {
            daysToCompute = 1
        }
        computeCommute()
    }
    
    @IBAction func commuteTypeChanged(_ sender: UISegmentedControl) {
        computeCommute()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        milesTextField.delegate = self
    }
    
    private func computeCommute() {
        var miles: Float
        var commuteTime: Float
        var gasToPurchase: Float
        
        if (milesTextField.text!.isEmpty) {
            miles = 0
        }
        else {
            miles = Float(milesTextField.text!)!
        }
        switch commuteTypeControl.selectedSegmentIndex {
        case 1:
            commuteTime = miles / averageBusSpeed * daysToCompute
            gasToPurchase = 0
            commuteTypeImage.image = UIImage(named: "bus_icon")
        case 2:
            commuteTime = miles / averageBikeSpeed * daysToCompute
            gasToPurchase = 0
            commuteTypeImage.image = UIImage(named: "bike_icon")
        default:
            commuteTime = miles / averageCarSpeed * daysToCompute
            gasToPurchase = miles / averageCarMpg * daysToCompute
            commuteTypeImage.image = UIImage(named: "car_icon")
            if (gasToPurchase > currentGas) {
                let gasDiff: Float = gasToPurchase - currentGas
                let lowGasAlert = UIAlertController(title: "Warning", message: "You don't currently have enough gas in your car for the calculated commute. Add " + String(format: "%.3f", gasDiff) + " gallons of gas to your tank to make it without needing to fill up.", preferredStyle: UIAlertController.Style.alert)
                let cancelAction = UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: nil)
                lowGasAlert.addAction(cancelAction)
                let okAction = UIAlertAction(title: "Okay", style: UIAlertAction.Style.default, handler: {
                    action in
                    self.currentGas += gasDiff
                    self.currentGasLabel.text = String(format: "%.3f", self.currentGas) + " gallons"
                })
                lowGasAlert.addAction(okAction)
                present(lowGasAlert, animated: true, completion: nil)
            }
        }
        
        totalCommuteTimeLabel.text = String(format: "%.3f", commuteTime) + " hours"
        gasToPurchaseLabel.text = String(format: "%.3f", gasToPurchase) + " gallons"
    }
}

