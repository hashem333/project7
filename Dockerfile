FROM python:alpine3.17

RUN mkdir -p /home/app/dist

######## FRONT-END #########
# copy dist/index.html and dist/favicon to /home/app/dist
COPY dist /home/app/dist

# copy dist/js content and dist/css content to the image
COPY dist/js /home/app/dist/js
COPY dist/css /home/app/dist/css
############################


######## BACK_END ###########
COPY requirments.txt /home/app 
COPY backend.py /home/app
#############################



RUN cd /home/app && pip install --no-cache-dir -r requirments.txt

EXPOSE 8088

WORKDIR /home/app

CMD ["python", "backend.py" ]

