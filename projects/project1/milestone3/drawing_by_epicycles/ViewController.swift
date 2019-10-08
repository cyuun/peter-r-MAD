//
//  ViewController.swift
//  drawing_by_epicycles
//
//  Created by Peter Julian Rosenthal on 10/8/19.
//  Copyright © 2019 Peter Julian Rosenthal. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    
    @IBOutlet weak var epicycleView: EpicycleView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        epicycleView.circle0Center = CGPoint(x: epicycleView.bounds.width / 2,
                                             y: epicycleView.bounds.height / 2)
        epicycleView.drawTimer = Timer.scheduledTimer(withTimeInterval: 0.1, repeats: true) {
            timer in
            self.epicycleView.setNeedsDisplay()
        }
    }


}

