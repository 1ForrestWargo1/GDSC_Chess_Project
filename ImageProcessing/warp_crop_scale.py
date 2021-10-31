# https://www.pyimagesearch.com/2014/08/25/4-point-opencv-getperspective-transform-example/

from transform import four_point_transform
import numpy as np
import argparse
import cv2
from PIL import Image

# image_path = "img/img2.png"
image_path = "img/img5.png"

image = cv2.imread(image_path)

pts = np.array([(319, 100), (967, 96), (984, 745), (313, 753)]) #img5.png

# apply the four point tranform to obtain a "birds eye view" of
# the image
warped = four_point_transform(image, pts)

# scale down the perspective-warped image to specified coordinates
dimensions = (512, 512)
img_scaled = cv2.resize(warped, dimensions)

# split img_scaled into 64 cropped images, appended to 2D array images_separated
images_separated = []
l = int(dimensions[0] / 8)
for i in range(0,  8):
    row = []
    for j in range(0, 8):
        x = i * l
        y = j * l
        row.append(img_scaled[x:x+l, y:y+l])
    images_separated.append(row)

cv2.imshow("Original", image)
cv2.imshow("normalized", img_scaled)

# show each of the split images after a keypress
row_number = 0
col_number = 0
for row in images_separated:
    for img in row:
        cv2.imshow("Row: " + str(row_number) + ", Col: " + str(col_number), img)
        cv2.waitKey(0)
        col_number += 1
    row_number += 1
