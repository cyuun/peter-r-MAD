
//
//  ViewController.swift
//  lab3
//
//  Created by Peter Rosental on 23/09/2019.
//  Copyright © 2019 Peter Rosental. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var hedgePic: UIImageView!
    @IBOutlet weak var hedgeLabel: UILabel!
    @IBOutlet weak var hedgeControl: UISegmentedControl!
    @IBOutlet weak var capsSwitch: UISwitch!
    
    @IBAction func changeSegment(_ sender: UISegmentedControl) {
        switch hedgeControl.selectedSegmentIndex {
        case 0:
            hedgePic.image = UIImage(named: "happy_hedge")
            hedgeLabel.text = "Happy Hedgehog"
            hedgeLabel.textColor = UIColor(red: 0, green: 1, blue: 0, alpha: 1)
        case 1:
            hedgePic.image = UIImage(named: "hedge")
            hedgeLabel.text = "Hedgehog"
            hedgeLabel.textColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
        case 2:
            hedgePic.image = UIImage(named: "anger_hedge")
            hedgeLabel.text = "Angry Hedgehog"
            hedgeLabel.textColor = UIColor(red: 1, green: 0, blue: 0, alpha: 1)
        default:
            hedgePic.image = UIImage(named: "hedge")
            hedgeLabel.text = "Hedgehog"
            hedgeLabel.textColor = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
        }
        changeCaps(capsSwitch)
    }
    
    @IBAction func changeCaps(_ sender: UISwitch) {
        if sender.isOn {
            hedgeLabel.text = hedgeLabel.text?.uppercased()
        } else {
            hedgeLabel.text = hedgeLabel.text?.lowercased()
        }
    }
    
    @IBAction func changeSize(_ sender: UISlider) {
        hedgeLabel.font = UIFont.systemFont(ofSize: CGFloat(sender.value))
    }
    
}

