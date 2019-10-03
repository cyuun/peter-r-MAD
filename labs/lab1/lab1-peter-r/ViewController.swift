//
//  ViewController.swift
//  lab1-peter-r
//
//  Created by Peter Rosental on 11/09/2019.
//  Copyright © 2019 Peter Rosental. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var viewNum = 2
    
    @IBOutlet weak var rocketLabel: UILabel!
    @IBOutlet weak var rocketImage: UIImageView!
    
    @IBAction func changeRocket(_ sender: UIButton) {
        viewNum += sender.tag
        if viewNum > 2 {
            viewNum = 0
        }
        if viewNum < 0 {
            viewNum = 2
        }
        switch viewNum {
        case 0:
            rocketImage.image = UIImage(named: "falcon9")
            rocketLabel.text  = "Falcon 9"
        case 1:
            rocketImage.image = UIImage(named: "falcon_heavy")
            rocketLabel.text  = "Falcon Heavy"
        case 2:
            rocketImage.image = UIImage(named: "ss_sh")
            rocketLabel.text  = "Star Ship / Super Heavy"
        default:
            viewNum = 0
        }
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

