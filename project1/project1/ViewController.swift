//
//  ViewController.swift
//  project1
//
//  Created by Peter Rosental on 30/09/2019.
//  Copyright © 2019 Peter Rosental. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var circlesView: CirclesView!
    
    @IBAction func sliderMoved(_ sender: UISlider) {
        circlesView.circleSize = CGFloat(sender.value)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

