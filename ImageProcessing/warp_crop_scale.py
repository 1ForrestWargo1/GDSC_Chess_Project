# https://www.pyimagesearch.com/2014/08/25/4-point-opencv-getperspective-transform-example/

from transform import four_point_transform
import numpy as np
import argparse
import cv2
from PIL import Image

def warp_crop_scale(image, points_for_warp):

    pts = np.array(points_for_warp)

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
            tmpImg = img_scaled[x:x+l, y:y+l]
            row.append(tmpImg)
            cv2.imwrite("./sliced-pieces/piece-" + str(i) + "-" + str(j) + ".png", tmpImg)
        images_separated.append(row)

    # cv2.imshow("Original", image)

    cv2.imwrite("./normalized.png", img_scaled)

    # show each of the split images after a keypress
    # row_number = 0
    # col_number = 0
    # for row in images_separated:
    #     for img in row:
    #         cv2.imshow("Row: " + str(row_number) + ", Col: " + str(col_number), img)
    #         cv2.waitKey(0)
    #         col_number += 1
    #     row_number += 1
    #     col_number = 0

    # cv2.waitKey()
    
    # print('done')
    return