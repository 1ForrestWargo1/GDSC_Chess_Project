# https://www.pyimagesearch.com/2014/08/25/4-point-opencv-getperspective-transform-example/

from transform import four_point_transform
import numpy as np
import argparse
import cv2

# image_path = "img/img2.png"
image_path = "img/img5.png"

image = cv2.imread(image_path)

pts = np.array([(310, 91), (976, 90), (994, 754), (306, 760)]) #img5.png

# apply the four point tranform to obtain a "birds eye view" of
# the image
warped = four_point_transform(image, pts)

img_scaled = cv2.resize(warped, (512, 512))

# show the original and warped images
cv2.imshow("Original", image)
cv2.imshow("Warped", img_scaled)
cv2.waitKey(0)