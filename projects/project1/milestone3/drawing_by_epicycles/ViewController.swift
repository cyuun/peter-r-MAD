//
//  ViewController.swift
//  drawing_by_epicycles
//
//  Created by Peter Rosenthal on 10/8/19.
//  Copyright © 2019 Peter Rosenthal. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    
    @IBOutlet weak var epicycleView: EpicycleView!
    @IBOutlet weak var circle0Slider: UISlider!
    @IBOutlet weak var circle1Slider: UISlider!
    @IBOutlet weak var circle2Slider: UISlider!
    
    @IBAction func circle0SliderMoved(_ sender: Any) {
        epicycleView.circle0Multiplier = CGFloat(circle0Slider.value)
    }
    @IBAction func circle1SliderMoved(_ sender: Any) {
        epicycleView.circle1Multiplier = CGFloat(circle1Slider.value)
    }
    @IBAction func circle2SliderMoved(_ sender: Any) {
        epicycleView.circle2Multiplier = CGFloat(circle2Slider.value)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        epicycleView.circle0Center = CGPoint(x: epicycleView.bounds.width / 2,
                                             y: epicycleView.bounds.height / 2)
        epicycleView.drawTimer = Timer.scheduledTimer(withTimeInterval: 0.035, repeats: true) {
            timer in
            self.epicycleView.updateCircles(deltaTime: 0.035)
        }
    }


}

